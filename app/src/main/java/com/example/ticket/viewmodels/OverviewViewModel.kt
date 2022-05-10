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

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket.Data.HexaCode
import com.example.ticket.Data.HexaCodeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
@HiltViewModel
class OverviewViewModel @Inject constructor(

        private val api: HexaCodeApi.MarsApi.retrofitService
) : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request

    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
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
                _status.value = listResult[1].hexadecimal
                Log.e("OverviewModel","Yoooooo ${listResult}")

            } catch (e: Exception) {
                _status.value = "It failed ${e.message}"
            }

            }
    }
}
