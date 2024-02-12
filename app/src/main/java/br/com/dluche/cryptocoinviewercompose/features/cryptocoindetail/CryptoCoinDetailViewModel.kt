package br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail

import androidx.lifecycle.ViewModel
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinDetails
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString

class CryptoCoinDetailViewModel: ViewModel() {
}

data class CoinDetailsState(
    val coinId: String = String.emptyString(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = String.emptyString(),
    val coinDetail: CryptoCoinDetails = CryptoCoinDetails()
)