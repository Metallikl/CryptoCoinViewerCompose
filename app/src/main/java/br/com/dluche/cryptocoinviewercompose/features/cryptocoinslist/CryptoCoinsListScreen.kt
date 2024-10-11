package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.GeneratingTokens
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinType
import br.com.dluche.cryptocoinviewercompose.extentions.orDefault
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListEvent.LoadNextPage
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListEvent.SearchClick
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListEvent.SearchTextChange
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListEvent.TryAgain
import org.koin.androidx.compose.koinViewModel


@ExperimentalMaterial3Api
@Composable
fun CryptoCoinListRoute() {
    val viewmodel = koinViewModel<CryptoCoinListViewModel>()
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    CryptoCoinListScreen(
        uiState = uiState,
        onEvent = viewmodel::onEvent
    )
}


@ExperimentalMaterial3Api
@Composable
fun CryptoCoinListScreen(
    uiState: CryptoCoinListState,
    onEvent: (CryptoCoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CryptoCoin Viewer")
                },
//                actions = {
//                    var isSearchTextFieldVisible by remember {
//                        mutableStateOf(false)
//                    }
//                    var text by remember {
//                        mutableStateOf("")
//                    }
//
//                    AnimatedVisibility(visible = !isSearchTextFieldVisible) {
//                        Icon(
//                            imageVector = Icons.Outlined.Search,
//                            contentDescription = null,
//                            Modifier
//                                .padding(10.dp)
//                                .clickable {
//                                    isSearchTextFieldVisible = true
//                                    titleVisibility = !isSearchTextFieldVisible
//                                }
//                        )
//                    }
//                    AnimatedVisibility(visible = isSearchTextFieldVisible) {
//                        Icon(
//                            imageVector = Icons.Outlined.Close,
//                            contentDescription = null,
//                            Modifier
//                                .padding(10.dp)
//                                .clickable {
//                                    isSearchTextFieldVisible = false
//                                    text = ""
//                                    titleVisibility = !isSearchTextFieldVisible
//                                }
//                        )
//                    }
//
//                    BasicTextField(
//                        value = text,
//                        onValueChange = {
//                            text = it
//                        },
//                        Modifier.fillMaxWidth(
//                            animateFloatAsState(
//                                targetValue = if (isSearchTextFieldVisible) 1f else 0f,
//                                label = "largura animada do campo de texto"
//                            ).value
//                        ),
//                        decorationBox = { innerTextField ->
//                            if (text.isEmpty()) {
//                                Text(
//                                    text = "Nome da crypto",
//                                    style = TextStyle.Default.copy(
//                                        color = Color.Gray.copy(0.5f),
//                                        fontStyle = FontStyle.Italic
//                                    )
//                                )
//
//                            }
//                            innerTextField()
//                        }
//                    )
//                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                uiState = uiState,
                onEvent = onEvent
            )
            when {
                uiState.isLoading || uiState.isLoadingNextPage -> {
                    CryptoCoinListLoadingContent()
                }

                uiState.isError || uiState.isErrorNextPage -> {
                    ErrorDialogContent(uiState, onEvent, modifier.padding(paddingValues))
                }

                else -> {
                    CryptoCoinListContent(
                        uiState = uiState,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}

@Composable
fun CryptoCoinListLoadingContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            Modifier.fillMaxSize(0.5f)
        )
    }
}

@Composable
private fun CryptoCoinListContent(
    uiState: CryptoCoinListState,
    onEvent: (CryptoCoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val threshold = remember { 5 }
        val listState = rememberLazyListState()
        val isLastItemVisible by remember {
            derivedStateOf {
                //!listState.canScrollForward && listState.firstVisibleItemIndex != 0
                val layoutInfo = listState.layoutInfo
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItemIndex =
                    (layoutInfo.visibleItemsInfo.lastOrNull()?.index.orDefault()).plus(1)
                lastVisibleItemIndex > (totalItems - threshold)

            }
        }
        val isLoadingSomething by remember {
            derivedStateOf {
                (uiState.isLoading || uiState.isLoadingNextPage)
            }
        }
        LaunchedEffect(key1 = isLastItemVisible) {
            if (isLastItemVisible && !isLoadingSomething) {
                onEvent(LoadNextPage)
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(16.dp),
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
fun SearchBar(
    uiState: CryptoCoinListState,
    onEvent: (CryptoCoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary
            )
    ) {
        BasicTextField(
            value = uiState.search.orEmpty(),
            onValueChange = {
                onEvent(SearchTextChange(it))
            },
            modifier = Modifier.weight(2f),
            decorationBox = { innerTextField ->
                if (uiState.search.orEmpty().isEmpty()) {
                    Text(
                        text = "Nome da crypto",
                        style = TextStyle.Default.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Icon(
            imageVector = Icons.Outlined.QueryStats,
            contentDescription = "Icone de busca",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    onEvent(SearchClick)
                }
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SearchBarPreview() {
    SearchBar(
        uiState = CryptoCoinListState(),
        onEvent = {}
    )
}


@Composable
fun ErrorDialogContent(
    uiState: CryptoCoinListState,
    onEvent: (CryptoCoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
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
                        onEvent(TryAgain)
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
    ErrorDialogContent(
        uiState = CryptoCoinListState(message = "Erro: sem internet"),
        onEvent = { }
    )
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
        ),
        onEvent = { }
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

