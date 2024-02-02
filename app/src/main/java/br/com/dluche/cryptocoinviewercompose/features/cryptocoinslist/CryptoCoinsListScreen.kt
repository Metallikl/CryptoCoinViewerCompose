package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinType

//
@ExperimentalMaterial3Api
@OptIn(ExperimentalFoundationApi::class)
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
        CryptoCoinListContent(modifier.padding(paddingValues), uiState)
    }
}

@Composable
private fun CryptoCoinListContent(modifier: Modifier, uiState: CryptoCoinListState) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            items(uiState.cryptoCoinList) { coin ->
                CryptoCoinCell(coin)
            }
        }
    }
}

@Composable
private fun CryptoCoinCell(coin: CryptoCoin) {
    Column(
        modifier = Modifier
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
        ){
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = if(coin.type == CryptoCoinType.COIN) Color.Green else Color.Red.copy(alpha = 0.8f)
            )

            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = null,
                tint = if(coin.isActive) Color.Green else Color.Red.copy(alpha = 0.8f)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun CryptoCoinScreenPreview() {
    CryptoCoinListScreen(uiState = CryptoCoinListState())
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
            isActive = true
        )
    )
}

