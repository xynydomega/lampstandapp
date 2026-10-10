package com.example.lampstand.data

import android.content.Context

/**
 * THE switch. The whole app reads the backend through here.
 *
 * - Demo today: [useFakeBackend] = true → [FakeAuthRepository]
 *   (persisted to SharedPreferences, so accounts survive restarts).
 * - Real backend tomorrow: write `class RealAuthRepository :
 *   AuthRepository`, return it below, flip [useFakeBackend] to
 *   false. Screens and MainActivity stay untouched.
 */
object ServiceLocator {

    var useFakeBackend: Boolean = true

    private const val PREFS_NAME = "lampstand_mock_auth"

    private var appContext: Context? = null

    /** Call once from the UI layer so the mock DB can persist. */
    fun init(context: Context) {
        if (appContext == null) {
            appContext = context.applicationContext
        }
    }

    private val fakeAuth: AuthRepository by lazy {
        val prefs = try {
            appContext?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        } catch (_: Exception) {
            null // e.g. Compose preview: fall back to pure in-memory
        }
        FakeAuthRepository(prefs)
    }

    val authRepository: AuthRepository
        get() = if (useFakeBackend) {
            fakeAuth
        } else {
            error("Real backend not plugged in — implement AuthRepository and return it here")
        }
}
