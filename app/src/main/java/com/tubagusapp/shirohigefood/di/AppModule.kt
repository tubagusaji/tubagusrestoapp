package com.tubagusapp.shirohigefood.di

import com.tubagusapp.core.domain.usecase.FoodInteractor
import com.tubagusapp.core.domain.usecase.IFoodUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor): IFoodUseCase
}