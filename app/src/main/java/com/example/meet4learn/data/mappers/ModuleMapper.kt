package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.ModuleDTO
import com.example.meet4learn.domain.models.Module
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

fun ModuleDTO.toModel(): Module {
    return Module(
        id = id,
        courseId = courseId,
        title = title,
        scheduledAt = OffsetDateTime.parse(scheduled_at),
        description = description,
        status = status
    )
}

fun Module.toDTO(): ModuleDTO {
    return ModuleDTO(
        id = id,
        courseId = courseId,
        title = title,
        scheduled_at = scheduledAt.format(formatter),
        description = description,
        status = status
    )
}