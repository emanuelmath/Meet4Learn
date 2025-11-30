package com.example.meet4learn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R

// Set of Material typography styles to start with
val Oswald = FontFamily(
    Font(R.font.oswald, FontWeight.Normal),
    Font(R.font.oswald_medium, FontWeight.Medium),
    Font(R.font.oswald_bold, FontWeight.Bold),
    Font(R.font.oswald_semibold, FontWeight.SemiBold),
    Font(R.font.oswald_light, FontWeight.Light),
    Font(R.font.oswald_extralight, FontWeight.ExtraLight)
)
private val baseTypography = Typography()

val Typography = Typography(
    displayLarge = baseTypography.displayLarge.copy(fontFamily = Oswald),
    displayMedium = baseTypography.displayMedium.copy(fontFamily = Oswald),
    displaySmall = baseTypography.displaySmall.copy(fontFamily = Oswald),

    headlineLarge = baseTypography.headlineLarge.copy(fontFamily = Oswald),
    headlineMedium = baseTypography.headlineMedium.copy(fontFamily = Oswald),
    headlineSmall = baseTypography.headlineSmall.copy(fontFamily = Oswald),

    titleLarge = baseTypography.titleLarge.copy(fontFamily = Oswald),
    titleMedium = baseTypography.titleMedium.copy(fontFamily = Oswald),
    titleSmall = baseTypography.titleSmall.copy(fontFamily = Oswald),

    bodyLarge = baseTypography.bodyLarge.copy(fontFamily = Oswald),
    bodyMedium = baseTypography.bodyMedium.copy(fontFamily = Oswald),
    bodySmall = baseTypography.bodySmall.copy(fontFamily = Oswald),

    labelLarge = baseTypography.labelLarge.copy(fontFamily = Oswald),
    labelMedium = baseTypography.labelMedium.copy(fontFamily = Oswald),
    labelSmall = baseTypography.labelSmall.copy(fontFamily = Oswald)
)


    /*bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )*/
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
//)