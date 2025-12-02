package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.SavedPaymentMethodDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.models.SavedPaymentMethod
import com.example.meet4learn.domain.repositories.SavedPaymentMethodRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class SavedPaymentMethodRepositoryImpl(private val supabaseClient: SupabaseClient) : SavedPaymentMethodRepository {
    override suspend fun getAllCardsOfStudent(studentId: String): List<SavedPaymentMethod> {
        return supabaseClient.from("saved_payment_methods").select(Columns.ALL) {
            filter { eq("user_id", studentId) }
        }.decodeList<SavedPaymentMethodDTO>().map { it.toModel() }
    }

    override suspend fun saveCard(userId: String,
                                  lastFourDigits: String,
                                  brand: String,
                                  expMonth: Int,
                                  expYear: Int
    ) {
        val newCardDto = SavedPaymentMethodDTO(
            user_id = userId,
            last_four_digits = lastFourDigits,
            brand = brand,
            expiration_month = expMonth,
            expiration_year = expYear
        )

        supabaseClient.from("saved_payment_methods").insert(newCardDto)
    }

}