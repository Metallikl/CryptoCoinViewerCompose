package br.com.dluche.cryptocoinviewercompose.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail.CryptoCoinDetailRoute
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListRoute
import br.com.dluche.cryptocoinviewercompose.navigation.CryptoCoinViewerComposeRoutes.*

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun CryptoCoinViewerComposeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CryptoCoinList
    ) {
        composable<CryptoCoinList> {
            CryptoCoinListRoute{ coinId->
                navController.navigate(
                    CryptoCoinDetail(coinId)
                )
            }
        }

        composable<CryptoCoinDetail> { backStackEntry ->
            val coinId = backStackEntry.toRoute<CryptoCoinDetail>()
            CryptoCoinDetailRoute(coinId.coinId)
        }
    }
}