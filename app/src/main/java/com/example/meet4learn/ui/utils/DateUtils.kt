package com.example.meet4learn.ui.utils

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object DateUtils {

    fun canJoinClass(
        status: String,
        startTimeIso: String,
        now: OffsetDateTime = OffsetDateTime.now()
    ): ClassJoinStatus {
        if (status.trim().equals("finalizado", ignoreCase = true)) {
            return ClassJoinStatus.Finished
        }

        return try {
            val start = OffsetDateTime.parse(startTimeIso)
            val windowStart = start.minus(10, ChronoUnit.MINUTES)
            val windowEnd = start.plus(2, ChronoUnit.HOURS)

            when {
                now.isBefore(windowStart) -> ClassJoinStatus.NotStartedYet
                now.isAfter(windowEnd) -> ClassJoinStatus.Expired
                else -> ClassJoinStatus.Open
            }
        } catch (e: Exception) {
            ClassJoinStatus.Expired
        }
    }

    fun formatTimeMessage(isoDate: String): String {
        return try {
            val offsetTime = OffsetDateTime.parse(isoDate)
            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                .withZone(ZoneId.systemDefault())
            formatter.format(offsetTime)
        } catch (e: Exception) {
            "--:--"
        }
    }

    fun formatTimeClass(isoDate: String): String {
        return try {
            val offsetTime = OffsetDateTime.parse(isoDate)
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")
                .withZone(ZoneId.systemDefault())
            formatter.format(offsetTime)
        } catch (e: Exception) {
            "--:--"
        }
    }
}

enum class ClassJoinStatus {
    NotStartedYet,
    Open,
    Finished,
    Expired
}