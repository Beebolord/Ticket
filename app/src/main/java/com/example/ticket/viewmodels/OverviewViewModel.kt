/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ticket.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket.Data.HexaCode
import com.example.ticket.Data.HexaCodeApi
import com.example.ticket.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
@RequiresApi(Build.VERSION_CODES.O)
class OverviewViewModel() : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val errorChannel = Channel<UiText>()
    val errors = errorChannel.receiveAsFlow()

    private val _status = MutableLiveData<List<HexaCode>>()
    // The external immutable LiveData for the request status
    val status: LiveData<List<HexaCode>> = _status

    var customerName: MutableLiveData<String> = MutableLiveData("")
    var firstColorCode : MutableLiveData<Long> = MutableLiveData(0 )
    var SecondColorCode : MutableLiveData<Long> = MutableLiveData(0 )


    private var now = ""


    var startDt : String = ""
    var endDt : String = ""
    var nowDt : String = ""
    var nowTime : String = ""

    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        viewModelScope.launch() {
            try {
                val listResult : List<HexaCode> = HexaCodeApi.MarsApi.retrofitService.getPosts()
                _status.value = listResult
                customerName.value = listResult.get(0).hexadecimal.toString()

                firstColorCode.value = listResult.get(1).hexadecimal
                SecondColorCode.value = listResult.get(0).hexadecimal
                val calendar = Calendar.getInstance()
                calendar.time = Date()
                calendar.add(Calendar.HOUR_OF_DAY, 1.5.toInt())


                Log.e("OverviewModel","Yoooooo ${listResult.get(0).hexadecimal}")

            } catch (e: Exception) {
                Log.e("OverviewModel","Yoooooo ")

            }


        }
    }
}
