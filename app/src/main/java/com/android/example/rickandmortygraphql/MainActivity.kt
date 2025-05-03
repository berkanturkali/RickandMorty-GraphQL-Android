package com.android.example.rickandmortygraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.example.rickandmortygraphql.navigation.RickAndMortyDestinations
import com.android.example.rickandmortygraphql.presentation.characters.screen.CharactersScreen
import com.android.example.rickandmortygraphql.presentation.characters.viewmodel.CharactersScreenViewModel
import com.android.example.rickandmortygraphql.presentation.filter.screen.CharacterFilterOptionsScreen
import com.android.example.rickandmortygraphql.presentation.filter.screen.FilterScreen
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

const val SELECTED_FILTER_ARG_KEY = "selected_filter"
const val SET_BADGE_ON_FILTERS_KEY = "set_badge_on_filters"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val focusManager = LocalFocusManager.current

            val context = LocalContext.current
            RickAndMortyGraphQLTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    focusManager.clearFocus(true)
                                }
                            },
                        containerColor = MaterialTheme.rickAndMortyColors.background.primary
                    ) { innerPadding ->

                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = RickAndMortyDestinations.CharactersScreen
                        ) {
                            composable<RickAndMortyDestinations.CharactersScreen>(
                                enterTransition = {
                                    slideInHorizontally(
                                        initialOffsetX = { -it },
                                        animationSpec = spring()
                                    )
                                },
                                exitTransition = {
                                    slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = spring()
                                    )
                                },
                            ) {
                                val viewModel = hiltViewModel<CharactersScreenViewModel>()
                                val setBadgeOnFilters = it.savedStateHandle.get<Boolean>(
                                    SET_BADGE_ON_FILTERS_KEY
                                )
                                CharactersScreen(
                                    setBadgeOnFilters = setBadgeOnFilters,
                                    viewModel = viewModel,
                                    onCharacterClick = { id ->
                                        navController.navigate(
                                            RickAndMortyDestinations.CharacterDetailsScreen(
                                                id
                                            )
                                        )
                                    },
                                    navigateToFilterScreen = {
                                        it.savedStateHandle[SET_BADGE_ON_FILTERS_KEY] = null
                                        navController.navigate(RickAndMortyDestinations.CharacterFilterOptionsScreen)
                                    })
                            }

                            composable<RickAndMortyDestinations.CharacterFilterOptionsScreen>(
                                enterTransition = {
                                    slideInHorizontally(
                                        initialOffsetX = { -it },
                                        animationSpec = spring()
                                    )
                                },
                                exitTransition = {
                                    slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = spring()
                                    )
                                },
                            ) {
                                val selectedFilter = it.savedStateHandle.get<String>(
                                    SELECTED_FILTER_ARG_KEY
                                )
                                CharacterFilterOptionsScreen(
                                    selectedFilter = selectedFilter,
                                    navigateUp = navController::navigateUp,
                                    onCheckMarkClick = {
                                        navController.previousBackStackEntry?.savedStateHandle?.set(
                                            SET_BADGE_ON_FILTERS_KEY,
                                            true
                                        )
                                    },
                                    onFilterOptionClick = { filter ->
                                        val title = context.getString(filter.type.titleResId)
                                        navController.navigate(
                                            RickAndMortyDestinations.CharacterFiltersScreen(
                                                title = title,
                                                filter.options.map {
                                                    it.toString()
                                                },
                                                previouslySelectedFilter = filter.selectedFilter
                                            )
                                        )
                                    },
                                )
                            }

                            composable<RickAndMortyDestinations.CharacterFiltersScreen>(
                                enterTransition = {
                                    slideInHorizontally(initialOffsetX = { -it })
                                },
                                exitTransition = {
                                    slideOutHorizontally(targetOffsetX = { it })
                                },
                            ) {
                                val title =
                                    it.toRoute<RickAndMortyDestinations.CharacterFiltersScreen>().title
                                val filters =
                                    it.toRoute<RickAndMortyDestinations.CharacterFiltersScreen>().filters

                                val previouslySelectedFilter =
                                    it.toRoute<RickAndMortyDestinations.CharacterFiltersScreen>().previouslySelectedFilter

                                FilterScreen(
                                    title = title,
                                    filters = filters,
                                    previouslySelectedFilter = previouslySelectedFilter,
                                    onBackButtonClick = {
                                        setSelectedFilter(navController, previouslySelectedFilter)
                                        navController.navigateUp()
                                    },
                                    onCheckMarkClick = { selectedFilter ->
                                        setSelectedFilter(navController, selectedFilter)
                                        navController.navigateUp()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setSelectedFilter(navController: NavHostController, filter: String?) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            SELECTED_FILTER_ARG_KEY,
            filter
        )
    }
}
