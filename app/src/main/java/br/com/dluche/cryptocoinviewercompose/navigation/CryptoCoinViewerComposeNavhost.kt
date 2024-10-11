package br.com.dluche.cryptocoinviewercompose.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListRoute

@ExperimentalMaterial3Api
@Composable
fun CryptoCoinViewerComposeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CryptoCoinViewerComposeRoutes.CryptoCoinList
    ) {
        composable<CryptoCoinViewerComposeRoutes.CryptoCoinList> {
            CryptoCoinListRoute()
        }
    }
}