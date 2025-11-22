package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.ProfileDTO
import com.example.meet4learn.domain.models.Profile

fun ProfileDTO.toModel(): Profile {
    return Profile(
        id = id,
        fullName = full_name,
        role = role,
        balance = balance
    )
}

fun Profile.toDTO(): ProfileDTO {
    return ProfileDTO(
        id = id,
        full_name = fullName,
        role = role,
        balance = balance
    )
}