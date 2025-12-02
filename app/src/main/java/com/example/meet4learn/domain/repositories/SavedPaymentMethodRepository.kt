package com.example.meet4learn.domain.repositories

import com.example.meet4learn.domain.models.SavedPaymentMethod

interface SavedPaymentMethodRepository {
    //suspend fun getCardByStudentId(studentId: String): SavedPaymentMethod?
    suspend fun getAllCardsOfStudent(studentId: String): List<SavedPaymentMethod>
    suspend fun saveCard(userId: String,
                         lastFourDigits: String,
                         brand: String,
                         expMonth: Int,
                         expYear: Int
    )
}