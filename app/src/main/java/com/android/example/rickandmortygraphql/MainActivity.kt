package com.android.example.rickandmortygraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.example.rickandmortygraphql.navigation.CharactersScreen
import com.android.example.rickandmortygraphql.presentation.characters.screen.CharactersScreen
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
                        startDestination = CharactersScreen
                    ) {
                        composable<CharactersScreen> {
                            CharactersScreen()
                        }
                    }
                }
            }
        }
    }
}
