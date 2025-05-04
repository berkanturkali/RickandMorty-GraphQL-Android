package com.android.example.rickandmortygraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.example.rickandmortygraphql.navigation.RickAndMortyDestinations
import com.android.example.rickandmortygraphql.presentation.character.screen.CharacterScreen
import com.android.example.rickandmortygraphql.presentation.characters.screen.CharactersScreen
import com.android.example.rickandmortygraphql.presentation.characters.viewmodel.CharactersScreenViewModel
import com.android.example.rickandmortygraphql.presentation.components.RickAndMortyBottomBar
import com.android.example.rickandmortygraphql.presentation.episodes.screen.EpisodesScreen
import com.android.example.rickandmortygraphql.presentation.filter.screen.CharacterFilterOptionsScreen
import com.android.example.rickandmortygraphql.presentation.filter.screen.FilterScreen
import com.android.example.rickandmortygraphql.presentation.locations.screen.LocationsScreen
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

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val showBottomBar = when (navBackStackEntry?.destination?.route) {
                RickAndMortyDestinations.CharactersScreen::class.qualifiedName,
                RickAndMortyDestinations.LocationsScreen::class.qualifiedName,
                RickAndMortyDestinations.EpisodesScreen::class.qualifiedName -> true

                else -> false
            }
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
                        containerColor = MaterialTheme.rickAndMortyColors.background.primary,
                        bottomBar = {
                            AnimatedVisibility(
                                visible = showBottomBar,
                                enter = slideInVertically { fullHeight -> fullHeight },
                                exit = slideOutVertically { fullHeight -> fullHeight },
                            ) {
                                RickAndMortyBottomBar(onItemClick = {
                                    navController.navigate(it) {
                                        popUpTo(RickAndMortyDestinations.CharactersScreen) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                })
                            }
                        }
                    ) { innerPadding ->

                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = RickAndMortyDestinations.CharactersScreen
                        ) {

                            //region 🔵 CharactersScreen 🔵
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

                            //endregion

                            //region 🔵 CharacterFilterOptionsScreen 🔵
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

                            //endregion

                            //region 🔵 CharacterFilterScreen 🔵

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
                                    },
                                    onCheckMarkClick = { selectedFilter ->
                                        setSelectedFilter(navController, selectedFilter)
                                        navController.navigateUp()
                                    }
                                )
                            }

                            //endregion

                            //region 🔵 CharacterScreen 🔵

                            composable<RickAndMortyDestinations.CharacterDetailsScreen>(
                                enterTransition = {
                                    slideInHorizontally(initialOffsetX = { -it })
                                },
                                exitTransition = {
                                    slideOutHorizontally(targetOffsetX = { it })
                                },
                            ) {
                                CharacterScreen()
                            }
                            //endregion

                            //region 🔵 LocationsScreen 🔵

                            composable<RickAndMortyDestinations.LocationsScreen>(
                                enterTransition = {
                                    slideInHorizontally(initialOffsetX = { -it })
                                },
                                exitTransition = {
                                    slideOutHorizontally(targetOffsetX = { it })
                                },
                            ) {
                                LocationsScreen()
                            }
                            //endregion

                            //region 🔵 EpisodesScreen 🔵
                            composable<RickAndMortyDestinations.EpisodesScreen>(
                                enterTransition = {
                                    slideInHorizontally(initialOffsetX = { -it })
                                },
                                exitTransition = {
                                    slideOutHorizontally(targetOffsetX = { it })
                                },
                            ) {
                                EpisodesScreen()
                            }

                            //endregion

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
