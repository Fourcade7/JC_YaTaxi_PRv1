package com.pr7.jc_biztaxi_v4.ui.home.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor2

private val LightColorScheme = lightColorScheme(
    primary = StatusBarColor,
    secondary = PurpleGrey40,
    //for title
    tertiary = Color.Black,
    //layoutbackgroundcolor
    background = LayoutbackgroundColors,
    //layoutbackgroundcolor2
    onBackground = Color.White,
    onSecondary = StatusBarColor,

    //card background
    onPrimary = Color.Black,

    //diveder
    onTertiary=Color(0x80B2B5BE),


    onSurface = Color(0xFFD9D7DF),

    /* Other default colors to override

    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val DarkColorScheme = darkColorScheme(
    primary = StatusBarColor,
    secondary = PurpleGrey80,
    //for title
    tertiary = Color.White,
    //Layoutbackgroundcolor
    background = StatusBarColor,
    //layoutbackgroundcolor2
    onBackground = Color(0x1AFFFFFF),

    onSecondary = StatusBarColor,
    //card background
    onPrimary = StatusBarColor,
    //diveder
    onTertiary= Color(0x80B2B5BE),

            onSurface = Color(0xFF121114),


)


@Composable
fun JC_YaTaxi_PRv1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}