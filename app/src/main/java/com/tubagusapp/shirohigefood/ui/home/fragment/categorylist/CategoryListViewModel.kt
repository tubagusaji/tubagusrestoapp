package com.tubagusapp.shirohigefood.ui.home.fragment.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tubagusapp.core.domain.usecase.IFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val foodUseCase: IFoodUseCase) :
    ViewModel() {
    fun getCategories() = foodUseCase.getCategories().asLiveData()
}