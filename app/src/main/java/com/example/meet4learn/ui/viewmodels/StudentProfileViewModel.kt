package com.example.meet4learn.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.domain.repositories.SavedPaymentMethodRepository
import kotlinx.coroutines.launch
import java.util.Calendar

class StudentProfileViewModel(private val savedPaymentMethodRepository: SavedPaymentMethodRepository,
    private val profileRepository: ProfileRepository, private val authRepository: AuthRepository) : ViewModel() {

    var uiState by mutableStateOf(StudentProfileUiState())
        private set

    private val id: String by lazy {
        authRepository.getCurrentUserId() ?: ""
    }

    init {
        if (id.isNotEmpty()) {
            getMyData()
        }
    }

    fun getMyData(isRefresh: Boolean = false) {
        viewModelScope.launch {
            uiState = if (isRefresh) uiState.copy(isRefresh = true)
            else uiState.copy(isLoading = true)

            try {
                if (id.isNotEmpty()) {
                    val emailUser = authRepository.getCurrentUserEmail() ?: "Obteniendo correo..."

                    val userProfile = profileRepository.getStudentById(id)

                    val userCards = savedPaymentMethodRepository.getAllCardsOfStudent(id)

                    uiState = uiState.copy(
                        profileStudent = userProfile,
                        email = emailUser,
                        cards = userCards,
                        isLoading = false,
                        isRefresh = false
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    isRefresh = false,
                    errorMessage = "Error al cargar perfil: ${e.message}."
                )
            }
        }
    }

    fun toggleAddCardDialog(isOpen: Boolean) {
        uiState = uiState.copy(isAddCardDialogOpen = isOpen)
        if (!isOpen) {
            uiState = uiState.copy(newCardName = "", newCardNumber = "", newCardExpiry = "", newCardCvv = "")
        }
    }

    fun updateCardInput(name: String? = null, number: String? = null, expiry: String? = null,
                        cvv: String? = null, conditionNumber: Boolean = false) {
        uiState = uiState.copy(
            newCardName = name ?: uiState.newCardName,
            newCardNumber = if (conditionNumber) {
                if (number != null && (number.length <= 16 || number.length < uiState.newCardNumber.length)) {
                        number
                }   else {
                        uiState.newCardNumber
                }
            } else {
                number ?: uiState.newCardNumber
            },
            newCardExpiry = expiry ?: uiState.newCardExpiry,
            newCardCvv = if (conditionNumber) {
                if (cvv != null && (cvv.length <= 4 || cvv.length < uiState.newCardCvv.length)) {
                    cvv
                }   else {
                    uiState.newCardCvv
                }
            } else {
               cvv ?: uiState.newCardCvv
            }
        )
    }

    fun saveNewCard() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            try {
                if (uiState.newCardNumber.length < 15 || uiState.newCardExpiry.length < 4
                    || uiState.newCardName.isBlank()) {
                    throw Exception("Datos incompletos.")
                }

                val dateParts = uiState.newCardExpiry.split("/")
                if (dateParts.size != 2) throw Exception("Formato de fecha inválido.")

                val month = dateParts[0].toIntOrNull() ?: 0
                val yearShort = dateParts[1].toIntOrNull() ?: 0
                val yearFull = 2000 + yearShort

                val calendar = Calendar.getInstance()
                val currentYear = calendar.get(Calendar.YEAR)
                val currentMonth = calendar.get(Calendar.MONTH) + 1

                if (yearFull < currentYear || (yearFull == currentYear && month < currentMonth) || month !in 1..12) {
                    throw Exception("Fecha inválida o tarjeta vencida.")
                }

                val lastFour = uiState.newCardNumber.takeLast(4)

                val brandDetected = detectBrand(uiState.newCardNumber)

                if (id.isNotEmpty()) {
                    savedPaymentMethodRepository.saveCard(
                        userId = id,
                        lastFourDigits = lastFour,
                        brand = brandDetected,
                        expMonth = month,
                        expYear = yearFull
                    )

                    toggleAddCardDialog(false)
                    uiState = uiState.copy(errorMessage = null)
                    getMyData(isRefresh = true)
                }
            } catch (e: Exception) {
                Log.e("SaveCard", "Error guardando tarjeta.", e)
                uiState = uiState.copy(
                    errorMessage = e.message ?: "Error desconocido al guardar",
                    isLoading = false
                )
                } finally {
                    uiState = uiState.copy(isLoading = false)
                }
            }
        }

        private fun detectBrand(number: String): String {
            return when {
                number.startsWith("4") -> "VISA"
                number.startsWith("5") -> "MASTERCARD"
                number.startsWith("3") -> "AMEX"
                else -> "OTRA"
            }
        }

        fun clearError() {
            uiState = uiState.copy(errorMessage = null)
        }
    }
