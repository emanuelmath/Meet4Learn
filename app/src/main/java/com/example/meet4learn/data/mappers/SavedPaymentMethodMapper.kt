package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.SavedPaymentMethodDTO
import com.example.meet4learn.domain.models.SavedPaymentMethod

fun SavedPaymentMethodDTO.toModel(): SavedPaymentMethod {
    return SavedPaymentMethod(
        id = id ?: 0,
        userId = user_id,
        lastFourDigits = last_four_digits,
        brand = brand,
        expirationMonth = expiration_month,
        expirationYear = expiration_year
    )
}

fun SavedPaymentMethod.toDTO(): SavedPaymentMethodDTO {
    return SavedPaymentMethodDTO(
        id = id,
        user_id = userId,
        last_four_digits = lastFourDigits,
        brand = brand,
        expiration_month = expirationMonth,
        expiration_year = expirationYear
    )
}