package com.tubagusapp.core.data.remote

import com.tubagusapp.core.data.remote.network.APIResult
import com.tubagusapp.core.data.remote.network.APIService
import com.tubagusapp.core.data.remote.response.CategoriesResponse
import com.tubagusapp.core.data.remote.response.CategoryFilterResponse
import com.tubagusapp.core.data.remote.response.DetailLookupResponse
import com.tubagusapp.core.data.remote.response.SearchResponse
import com.tubagusapp.core.utils.CppUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    private val apiKey = CppUtil.generateAPIKey()

    suspend fun getCategories(): Flow<APIResult<CategoriesResponse>> {
        return flow {
            try {
                val response = apiService.categories(apiKey)

                response.categories?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailFood(foodId: String): Flow<APIResult<DetailLookupResponse>> {
        return flow {
            try {
                val response = apiService.detailFood(apiKey, foodId)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFilteredByCategory(category: String): Flow<APIResult<CategoryFilterResponse>> {
        return flow {
            try {
                val response = apiService.filteredByCategory(apiKey, category)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchFood(query: String): Flow<APIResult<SearchResponse>> {
        return flow {
            try {
                val response = apiService.searchFood(apiKey, query)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}