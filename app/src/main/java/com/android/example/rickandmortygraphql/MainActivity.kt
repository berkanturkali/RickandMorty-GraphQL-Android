package com.android.example.rickandmortygraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.example.rickandmortygraphql.navigation.RickAndMortyDestinations
import com.android.example.rickandmortygraphql.presentation.characters.screen.CharactersScreen
import com.android.example.rickandmortygraphql.presentation.filter.screen.CharacterFilterOptionsScreen
import com.android.example.rickandmortygraphql.presentation.filter.screen.FilterScreen
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RickAndMortyGraphQLTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
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
                            CharactersScreen(navigateToFilterScreen = {
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
                            CharacterFilterOptionsScreen(
                                navigateUp = navController::navigateUp,
                                onCheckMarkClick = {
                                    // TODO: save the filters to local
                                    navController.navigateUp()
                                },
                                onFilterOptionClick = { filter ->
                                    navController.navigate(
                                        RickAndMortyDestinations.CharacterFiltersScreen(
                                            title = filter.title,
                                            filter.options.map {
                                                it.toString()
                                            })
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
                            FilterScreen(
                                title = title,
                                filters = filters,
                                onBackButtonClick = navController::navigateUp,
                                onCheckMarkClick = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
