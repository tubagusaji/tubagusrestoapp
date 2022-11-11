package com.tubagusapp.core.utils

object CppUtil {
    init {
        System.loadLibrary("native-lib")
    }

    external fun generateAPIKey(): String
}