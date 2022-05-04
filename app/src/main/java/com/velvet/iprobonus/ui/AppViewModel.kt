package com.velvet.iprobonus.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.data.network.Network
import com.velvet.data.schemas.AuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel @Inject constructor(private val network: Network) : ViewModel() {

    private val _expirationDate : MutableLiveData<String> = MutableLiveData()
    val expirationDate: LiveData<String> = _expirationDate
    private val _expirationQuantity : MutableLiveData<String> = MutableLiveData()
    val expirationQuantity: LiveData<String> = _expirationQuantity
    private val _currentQuantity : MutableLiveData<String> = MutableLiveData()
    val currentQuantity: LiveData<String> = _currentQuantity

    init {
        viewModelScope.launch(Dispatchers.IO) {
            network.getBonuses(
                AuthRequest(
                clientId = "2c44d8c2-c89a-472e-aab3-9a8a29142315",
                accessToken = "",
                device = "device",
                deviceId = "7db72635-fd0a-46b9-813b-1627e3aa02ea",
                latitude = 0f,
                longitude = 0f,
                sourceQuery = 0
                )
            ).onSuccess { data ->
                _currentQuantity.postValue(data.currentQuantity.toString().substringBefore('.'))
                _expirationQuantity.postValue(data.forBurningQuantity.toString().substringBefore('.'))
                val date = data.dateBurning.subSequence(8, 10).toString() + "." + data.dateBurning.subSequence(5,7)
                _expirationDate.postValue(date)
            }.onFailure {
                _currentQuantity.postValue("?")
                _expirationQuantity.postValue("?")
                _expirationDate.postValue("?")
            }
        }
    }
}