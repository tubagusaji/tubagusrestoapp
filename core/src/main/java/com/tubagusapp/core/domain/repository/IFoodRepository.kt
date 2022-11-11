package com.tubagusapp.core.domain.repository

import com.tubagusapp.core.data.Resource
import com.tubagusapp.core.domain.model.Category
import com.tubagusapp.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {
    fun addFavorite(food: Food)

    fun getCategories(): Flow<Resource<List<Category>>>

    fun getFavorites(): Flow<Resource<List<Food>>>

    fun getFilteredByCategory(category: String): Flow<Resource<List<Food>>>

    fun getFoodDetail(foodId: String): Flow<Resource<Food>>

    fun removeFavorite(food: Food)

    fun searchFood(query: String): Flow<Resource<List<Food>>>
}