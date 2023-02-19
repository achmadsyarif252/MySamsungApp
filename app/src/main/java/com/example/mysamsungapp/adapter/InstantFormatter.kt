package com.example.mysamsungapp.adapter

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object InstantFormatter {
    fun formatTime(instant: Instant): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        return formatter.format(instant.atZone(ZoneId.systemDefault()))
    }
}