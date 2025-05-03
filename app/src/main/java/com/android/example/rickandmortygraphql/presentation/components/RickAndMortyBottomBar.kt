package com.android.example.rickandmortygraphql.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.model.BottomBarItems
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun RickAndMortyBottomBar(
    modifier: Modifier = Modifier,
) {

    var selectedItem by rememberSaveable {
        mutableStateOf(BottomBarItems.CHARACTERS)
    }

    Box {

        HorizontalDivider(
            thickness = 0.2.dp,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth(),
        )

        NavigationBar(
            modifier = modifier
                .fillMaxWidth(),
            containerColor = Color.Transparent,
        ) {
            BottomBarItems.entries.forEach { item ->
                NavigationBarItem(
                    selected = selectedItem == item,
                    onClick = {
                        selectedItem = item
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.title),
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.rickAndMortyColors.bottomBarColor.selected,
                        unselectedIconColor = MaterialTheme.rickAndMortyColors.bottomBarColor.unselected,
                        selectedTextColor = MaterialTheme.rickAndMortyColors.bottomBarColor.selected,
                        unselectedTextColor = MaterialTheme.rickAndMortyColors.bottomBarColor.unselected
                    ),
                )
            }
        }
    }

}

@Preview
@Composable
private fun RickAndMortyBottomBarPrev() {
    RickAndMortyGraphQLTheme {
        RickAndMortyBottomBar()
    }
}