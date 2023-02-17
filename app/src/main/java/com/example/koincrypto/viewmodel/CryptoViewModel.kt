package com.example.koincrypto.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koincrypto.model.CryptoModel
import com.example.koincrypto.repository.CryptoDownload
import com.example.koincrypto.util.Resource
import kotlinx.coroutines.*

class CryptoViewModel(
    private val cryptoDownloadRepo: CryptoDownload
) : ViewModel() {

    val cryptoList = MutableLiveData<Resource<List<CryptoModel>>>()
    val cryptoError = MutableLiveData<Resource<Boolean>>()
    val cryptoLoading = MutableLiveData<Resource<Boolean>>()

    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cryptoError.value = Resource.error(throwable.localizedMessage ?: "error", data = true)
    }

    fun getDataFromAPI() {
        cryptoLoading.value = Resource.loading(data = true)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val resource = cryptoDownloadRepo.downloadCryptos()
            withContext(Dispatchers.Main) {
                resource.data?.let {
                    cryptoList.value = resource
                    cryptoLoading.value = Resource.loading(data = false)
                    cryptoError.value = Resource.error("", data = false)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}