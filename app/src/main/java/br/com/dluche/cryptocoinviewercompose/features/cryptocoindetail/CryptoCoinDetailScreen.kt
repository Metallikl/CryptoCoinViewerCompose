package br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinDetails
import br.com.dluche.cryptocoinviewercompose.domain.model.Tag
import br.com.dluche.cryptocoinviewercompose.domain.model.Team
import coil.compose.AsyncImage


@ExperimentalLayoutApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoCoinDetailScreen(details: CryptoCoinDetails) {
    Column(
         modifier = Modifier
             .fillMaxSize()
             .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(text = "Detalhes ")
            },
            actions = {
            },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            CoinIcons(details)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = details.name,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = details.description,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                details.tags.forEach { tag ->
                    ChipTag(tag)
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            details.team.forEach { member->
                CoinDetailMember(member)
            }

        }
    }
}

@Composable
private fun CoinDetailMember(member: Team) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = member.name,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = member.position)
    }
}

@Composable
private fun CoinIcons(details: CryptoCoinDetails) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.30f)

    ) {
        AsyncImage(
            model = details.logo,
            contentDescription = null,
            placeholder = ColorPainter(Color.LightGray),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun ChipTag(tag: Tag) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(40))
            .background(Color.Magenta)
    ) {
        Text(
            text = tag.name.uppercase(),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(12.dp)
        )
    }
}


@ExperimentalLayoutApi
@Preview(showBackground = true)
@Composable
fun CryptoCoinDetailScreenPreview() {
    CryptoCoinDetailScreen(mockCoinDetails())
}

private fun mockCoinDetails(): CryptoCoinDetails {
    return CryptoCoinDetails(
        name = "BitCoin",
        logo = "https://picsum.photos/200",
        description = "Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson Lerem ipson ",
        tags = mockTags(),
        team = mockTeam()
    )
}

fun mockTeam(): List<Team> = listOf(
    Team(
        name = "Satoshi Nakamot",
        position = "Founder"
    ),
    Team(
        name = "Satoshi Nakamot",
        position = "Founder"
    ),
    Team(
        name = "Satoshi Nakamot",
        position = "Founder"
    ),
    Team(
        name = "Satoshi Nakamot",
        position = "Founder"
    ),
    Team(
        name = "Satoshi Nakamot",
        position = "Founder"
    ),
)

fun mockTags(): List<Tag> = listOf(
    Tag(name = "Seqwit"),
    Tag(name = "Cryptocurcecy"),
    Tag(name = "proof of work"),
    Tag(name = "payments"),
    Tag(name = "sha56"),
    Tag(name = "mining"),
    Tag(name = "linghtning network"),
)
