package com.demo.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.shoppinglist.data.ShopListRepositoryImpl
import com.demo.shoppinglist.domain.DeleteShopItemUseCase
import com.demo.shoppinglist.domain.EditShopItemUseCase
import com.demo.shoppinglist.domain.GetShopListUseCase
import com.demo.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()
    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }
    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

}