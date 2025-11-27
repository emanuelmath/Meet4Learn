package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.CourseDTO
import com.example.meet4learn.domain.models.Course
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CourseDTO.toModel(): Course {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    return Course(
        id = id,
        name = name,
        subject = subject,
        startDate = formatter.parse(start_date)!!,
        finishDate = formatter.parse(start_date)!!,
        price = price,
        teacherId = teacher_id,
        status = status
    )
}

fun Course.toDTO(): CourseDTO {
    return CourseDTO(
        id = id,
        name = name,
        subject = subject,
        start_date = startDate.toString(),
        finish_date = finishDate.toString(),
        price = price,
        teacher_id = teacherId,
        status = status
    )
}