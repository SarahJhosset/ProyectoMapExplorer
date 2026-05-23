package com.ucb.mapexplorer

import DsTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.mapexplorer.core.AppLanguage
import com.ucb.mapexplorer.core.LocalAppLanguage
import com.ucb.mapexplorer.core.LocalAppLocale
import com.ucb.mapexplorer.core.LocalLanguageController
import com.ucb.mapexplorer.navigation.AppNavHost

@Composable
@Preview
fun App() {
    val currentMode = ThemeMode.LIGHT
    val snackbarHostState = remember { SnackbarHostState() }
    
    // 1. Cargamos el idioma guardado O el del sistema si es la primera vez
    var language by remember { 
        val savedCode = getLanguageSetting()
        val initialLanguage = if (savedCode != null) {
            AppLanguage.fromCode(savedCode)
        } else {
            AppLanguage.fromCode(getSystemLanguageCode())
        }
        mutableStateOf(initialLanguage) 
    }

    // 2. Usamos 'key(language)' para forzar que toda la app se reinicie internamente 
    // cuando el estado del idioma cambie. Esto asegura que stringResource se actualice.
    key(language) {
        CompositionLocalProvider(
            LocalAppLanguage provides language,
            LocalLanguageController provides { newLanguage -> 
                language = newLanguage 
                saveLanguageSetting(newLanguage.code)
            },
            LocalAppLocale.provides(language.code)
        ) {
            DsTheme(
                mode = currentMode
            ) {
                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { paddingValues ->
                    AppNavHost()
                }
            }
        }
    }
}
