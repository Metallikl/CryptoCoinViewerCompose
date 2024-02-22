package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.GeneratingTokens
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinType

//
@ExperimentalMaterial3Api
@Composable
fun CryptoCoinListScreen(
    modifier: Modifier = Modifier,
    uiState: CryptoCoinListState
) {
    Scaffold(
        topBar = {
            var titleVisibility by remember {
                mutableStateOf(true)
            }
            TopAppBar(
                title = {
                    AnimatedVisibility(visible = titleVisibility) {
                        Text(text = "CryptoCoin Viewer")
                    }
                },
                actions = {
                    var isSearchTextFieldVisible by remember {
                        mutableStateOf(false)
                    }
                    var text by remember {
                        mutableStateOf("")
                    }

                    AnimatedVisibility(visible = !isSearchTextFieldVisible) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            Modifier
                                .padding(10.dp)
                                .clickable {
                                    isSearchTextFieldVisible = true
                                    titleVisibility = !isSearchTextFieldVisible
                                }
                        )
                    }
                    AnimatedVisibility(visible = isSearchTextFieldVisible) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            Modifier
                                .padding(10.dp)
                                .clickable {
                                    isSearchTextFieldVisible = false
                                    text = ""
                                    titleVisibility = !isSearchTextFieldVisible
                                }
                        )
                    }

                    BasicTextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        Modifier.fillMaxWidth(
                            animateFloatAsState(
                                targetValue = if (isSearchTextFieldVisible) 1f else 0f,
                                label = "largura animada do campo de texto"
                            ).value
                        ),
                        decorationBox = { innerTextField ->
                            if (text.isEmpty()) {
                                Text(
                                    text = "Nome da crypto",
                                    style = TextStyle.Default.copy(
                                        color = Color.Gray.copy(0.5f),
                                        fontStyle = FontStyle.Italic
                                    )
                                )

                            }
                            innerTextField()
                        }
                    )
                }
            )
        },
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                CryptoCoinListLoadingContent(uiState, modifier.padding(paddingValues))
            }

            uiState.isError -> {
                ErrorDialogContent(uiState, modifier.padding(paddingValues))
            }

            else -> {
                CryptoCoinListContent(modifier = modifier.padding(paddingValues), uiState = uiState)
            }
        }
    }
}

@Composable
fun CryptoCoinListLoadingContent(uiState: CryptoCoinListState, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier.fillMaxSize(0.5f)
        )
    }
}

@Composable
private fun CryptoCoinListContent(uiState: CryptoCoinListState, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(uiState.cryptoCoinList) { coin ->
                CryptoCoinCell(coin)
            }
        }
    }
}

@Composable
private fun CryptoCoinCell(coin: CryptoCoin, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Gray.copy(0.5f)
                ),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = coin.symbol
            )
            Text(
                text = coin.rank.toString()
            )

        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = coin.name,
            fontWeight = FontWeight.Bold

        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = cryptoCoinIconHandler(coin),
                contentDescription = null,
                tint = Color.DarkGray
            )

            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = null,
                tint = if (coin.isActive) Color.Green else Color.Red.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun SearchBar(onSearchClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(4.dp)
    ) {
        var inputText by remember {
            mutableStateOf("")
        }
        BasicTextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier.weight(2f)
        ) {

        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Icon(
            imageVector = Icons.Outlined.QueryStats,
            contentDescription = "Icone de busca",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    onSearchClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar({})
}


@Composable
fun ErrorDialogContent(uiState: CryptoCoinListState, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            modifier = modifier
                .fillMaxWidth(0.9f)
                .height(IntrinsicSize.Min)
                .padding(24.dp)
                .align(alignment = Alignment.Center)

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = null,
                    tint = Color.Red.copy(alpha = 0.6f),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Text(
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center,
                    text = uiState.message
                )
                OutlinedButton(
                    onClick = {
                        uiState.onTryAgain()
                    },
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Tentar Novamente"
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ErrorDialogContentPreview() {
    ErrorDialogContent(CryptoCoinListState(message = "Erro: sem internet"))
}

private fun cryptoCoinIconHandler(coin: CryptoCoin) =
    when (coin.type) {
        CryptoCoinType.COIN -> Icons.Outlined.MonetizationOn
        CryptoCoinType.TOKEN -> Icons.Outlined.GeneratingTokens
        CryptoCoinType.UNDEFINED -> Icons.Outlined.Casino
    }

@ExperimentalMaterial3Api
@Composable
@Preview
private fun CryptoCoinScreenPreview() {
    CryptoCoinListScreen(
        uiState = CryptoCoinListState(
            isError = true,
            cryptoCoinList = getCryptoCoinList()
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun CryptoCoinCellPreview() {
    CryptoCoinCell(
        coin = CryptoCoin(
            id = "XPTO",
            name = "XisPiriTo",
            symbol = "XPTO",
            isNew = true,
            rank = 1000,
            isActive = true,
            type = CryptoCoinType.UNDEFINED
        )
    )
}

private fun getCryptoCoinList() = listOf(
    CryptoCoin(
        id = "XPTO",
        name = "XisPiriTo",
        symbol = "XPTO",
        isNew = true,
        rank = 1000,
        isActive = true,
        type = CryptoCoinType.COIN
    ),
    CryptoCoin(
        id = "DOGC",
        name = "DogeCoin",
        symbol = "DOGC",
        isNew = false,
        rank = 500,
        isActive = true,
        type = CryptoCoinType.TOKEN
    ),
    CryptoCoin(
        id = "CtC",
        name = "CatCoin",
        symbol = "CATCO",
        isNew = true,
        rank = 10000,
        isActive = false,
        type = CryptoCoinType.UNDEFINED
    ),
    CryptoCoin(
        id = "XPTO",
        name = "XisPiriTo",
        symbol = "XPTO",
        isNew = true,
        rank = 1000,
        isActive = true,
        type = CryptoCoinType.COIN
    ),
    CryptoCoin(
        id = "DOGC",
        name = "DogeCoin",
        symbol = "DOGC",
        isNew = false,
        rank = 500,
        isActive = true,
        type = CryptoCoinType.TOKEN
    ),
    CryptoCoin(
        id = "CtC",
        name = "CatCoin",
        symbol = "CATCO",
        isNew = true,
        rank = 10000,
        isActive = false,
        type = CryptoCoinType.UNDEFINED
    ),
    CryptoCoin(
        id = "XPTO",
        name = "XisPiriTo",
        symbol = "XPTO",
        isNew = true,
        rank = 1000,
        isActive = true,
        type = CryptoCoinType.COIN
    ),
    CryptoCoin(
        id = "DOGC",
        name = "DogeCoin",
        symbol = "DOGC",
        isNew = false,
        rank = 500,
        isActive = true,
        type = CryptoCoinType.TOKEN
    ),
    CryptoCoin(
        id = "CtC",
        name = "CatCoin",
        symbol = "CATCO",
        isNew = true,
        rank = 10000,
        isActive = false,
        type = CryptoCoinType.UNDEFINED
    ),
    CryptoCoin(
        id = "XPTO",
        name = "XisPiriTo",
        symbol = "XPTO",
        isNew = true,
        rank = 1000,
        isActive = true,
        type = CryptoCoinType.COIN
    ),
    CryptoCoin(
        id = "DOGC",
        name = "DogeCoin",
        symbol = "DOGC",
        isNew = false,
        rank = 500,
        isActive = true,
        type = CryptoCoinType.TOKEN
    ),
    CryptoCoin(
        id = "CtC",
        name = "CatCoin",
        symbol = "CATCO",
        isNew = true,
        rank = 10000,
        isActive = false,
        type = CryptoCoinType.UNDEFINED
    )
)

