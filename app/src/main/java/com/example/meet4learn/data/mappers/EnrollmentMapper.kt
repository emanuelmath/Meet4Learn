package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.EnrollmentDTO
import com.example.meet4learn.domain.models.Enrollment

fun EnrollmentDTO.toModel(): Enrollment {
    return Enrollment(
        studentId = studentId,
        courseId = courseId
    )
}

fun Enrollment.toDTO(): EnrollmentDTO {
    return EnrollmentDTO(
        studentId = studentId,
        courseId = courseId
    )
}