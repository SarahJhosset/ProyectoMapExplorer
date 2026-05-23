package com.ucb.mapexplorer.map.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ucb.mapexplorer.core.AppLanguage
import com.ucb.mapexplorer.core.LocalAppLanguage
import com.ucb.mapexplorer.core.LocalLanguageController
import mapexplorer.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            MapSettingsContent()
        },
        sheetPeekHeight = 80.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContainerColor = Color.White,
        sheetDragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            MapViewContainer(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun MapSettingsContent() {
    val currentLanguage = LocalAppLanguage.current
    val changeLanguage = LocalLanguageController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(Res.string.moreOptions_tittle),
            style = AppTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        
        HorizontalDivider(modifier = Modifier.padding(bottom = 24.dp), color = Color.LightGray.copy(alpha = 0.5f))
        
        // SECCIÓN DE IDIOMA
        Text(
            text = stringResource(Res.string.moreOptions_subtittle_language),
            style = AppTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LanguageChip(
                text = stringResource(Res.string.moreOptions_optionText_spanish),
                isSelected = currentLanguage == AppLanguage.SPANISH,
                onClick = { changeLanguage(AppLanguage.SPANISH) }
            )
            LanguageChip(
                text = stringResource(Res.string.moreOptions_optionText_english),
                isSelected = currentLanguage == AppLanguage.ENGLISH,
                onClick = { changeLanguage(AppLanguage.ENGLISH) }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Espacio para futuras funciones
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Más funciones próximamente",
                style = AppTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun LanguageChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) AppTheme.colors.primary else Color.Gray.copy(alpha = 0.1f),
        contentColor = if (isSelected) Color.White else Color.Black
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            style = AppTheme.typography.bodyMedium
        )
    }
}
