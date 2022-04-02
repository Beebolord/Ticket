package com.example.ticket.screens.Component

sealed class StopwatchState() {
        data class Paused(
            val elapsedTime : Long
        ) : StopwatchState()

    data class running(
        val startTime : Long,
        val elapsedTime: Long
        ): StopwatchState()
    }
    interface TimestampProvider{
        fun getMilliseconds() : Long
}