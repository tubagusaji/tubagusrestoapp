package com.tubagusapp.core.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class DatabaseExecutor constructor(
    private val diskIO: Executor
) {
    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO
}