package br.com.dluche.cryptocoinviewercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListScreen
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListState
import br.com.dluche.cryptocoinviewercompose.ui.theme.CryptoCoinViewerComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCoinViewerComposeTheme {
               CryptoCoinListScreen(
                   uiState =  CryptoCoinListState()
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