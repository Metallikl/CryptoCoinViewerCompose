package br.com.dluche.cryptocoinviewercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListScreen
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListState
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListViewModel
import br.com.dluche.cryptocoinviewercompose.ui.theme.CryptoCoinViewerComposeTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //loadKoinModules(cryptoCoinListModule)
        setContent {
            CryptoCoinViewerComposeTheme {
                val viewmodel = koinViewModel<CryptoCoinListViewModel>()
                val uiState by viewmodel.uiState.collectAsState()
               CryptoCoinListScreen(
                   uiState =  CryptoCoinListState(uiState.cryptoCoinList)
               )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoCoinViewerComposeTheme {
        Greeting("Android")
    }
}