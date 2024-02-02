package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import androidx.lifecycle.ViewModel
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin

data class CryptoCoinListState(
    val cryptoCoinList: List<CryptoCoin> = emptyList()
)


class CryptoCoinListViewModel : ViewModel(){

}