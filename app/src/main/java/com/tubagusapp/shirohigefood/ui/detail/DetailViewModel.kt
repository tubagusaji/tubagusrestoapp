package com.tubagusapp.shirohigefood.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tubagusapp.core.domain.model.Food
import com.tubagusapp.core.domain.usecase.IFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val foodUseCase: IFoodUseCase) : ViewModel() {
    fun getFoodDetail(foodId: String) = foodUseCase.getFoodDetail(foodId).asLiveData()

    fun addFavorite(food: Food) = foodUseCase.addFavorite(food)

    fun removeFavorite(food: Food) = foodUseCase.removeFavorite(food)
}