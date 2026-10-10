package com.example.lampstand.data

import kotlinx.coroutines.flow.StateFlow

data class LampUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String
)

/**
 * Backend contract for auth + profile.
 *
 * The running app only talks to this interface — never to a
 * concrete backend. Today it is served by [FakeAuthRepository]
 * (in-memory, for demos); when the real backend lands, implement
 * this interface (e.g. Retrofit/Supabase/Firebase) and point
 * [ServiceLocator.authRepository] at it. No screen changes needed.
 */
interface AuthRepository {
    /** Null while signed out; every signed-in change re-emits. */
    val currentUser: StateFlow<LampUser?>

    suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ): Result<LampUser>

    suspend fun logIn(email: String, password: String): Result<LampUser>

    suspend fun logOut()

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phone: String
    ): Result<LampUser>
}
