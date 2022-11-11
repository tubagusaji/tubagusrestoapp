package com.tubagusapp.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tubagusapp.core.domain.model.Food
import com.tubagusapp.core.domain.usecase.IFoodUseCase

class FavoriteViewModel(private val foodUseCase: IFoodUseCase) : ViewModel() {
    fun getFavoriteList() = foodUseCase.getFavorites().asLiveData()

    fun addFavorite(food: Food) = foodUseCase.addFavorite(food)

    fun removeFavorite(food: Food) = foodUseCase.removeFavorite(food)
}