package com.example.restaurantapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    // Mapped from h1
    headlineLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color =LittleLemonColor.charcoal
    ),
    // Mapped from h2
    titleLarge = TextStyle(
        color = LittleLemonColor.charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    // Mapped from body1
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        color = LittleLemonColor.green,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp // You can adjust this if needed
    ),
    // Mapped from body2
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.green
    ),
    // Mapped from button text
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)