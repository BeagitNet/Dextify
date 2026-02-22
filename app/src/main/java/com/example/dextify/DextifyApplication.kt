/*
  Application entry point for Dextify.
  Initializes Hilt and sets up app-level dependency injection.
*/

package com.example.dextify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DexifyApplication : Application()
