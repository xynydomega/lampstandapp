package com.example.lampstand.data

import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

private const val KEY_ACCOUNTS = "accounts_json"
private const val KEY_SESSION_EMAIL = "session_email"

/**
 * In-memory backend for demos that ALSO persists to SharedPreferences,
 * so created accounts (and the signed-in session) survive app restarts.
 * Behaves like a real API (suspending calls, realistic delays,
 * credential errors) with zero infrastructure.
 *
 * Pass null prefs (previews/tests) for pure in-memory behaviour.
 *
 * Seeded demo account: demo@lampstand.app / password123
 */
class FakeAuthRepository(
    private val prefs: SharedPreferences? = null
) : AuthRepository {

    private val _currentUser = MutableStateFlow<LampUser?>(null)
    override val currentUser: StateFlow<LampUser?> = _currentUser.asStateFlow()

    private val accounts = mutableMapOf<String, Pair<LampUser, String>>()

    init {
        val demo = LampUser(
            firstName = "Demo",
            lastName = "User",
            email = "demo@lampstand.app",
            phone = "+234 801 0000 000"
        )
        accounts[demo.email] = demo to "password123"
        loadPersisted()
        Log.d("MockAuth", "ready: accounts=${accounts.size} session=${_currentUser.value?.email}")
    }

    override suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ): Result<LampUser> {
        delay(800) // simulate network
        val key = email.trim().lowercase()
        if (key.isBlank()) {
            return Result.failure(IllegalStateException("Please enter an email address"))
        }
        if (accounts.containsKey(key)) {
            return Result.failure(IllegalStateException("An account with this email already exists — try logging in"))
        }
        val user = LampUser(firstName.trim(), lastName.trim(), key, phone.trim())
        accounts[key] = user to password
        _currentUser.value = user
        persist()
        return Result.success(user)
    }

    override suspend fun logIn(email: String, password: String): Result<LampUser> {
        delay(800) // simulate network
        val record = accounts[email.trim().lowercase()]
            ?: return Result.failure(IllegalStateException("No account found for this email — try creating one"))
        if (record.second != password) {
            return Result.failure(IllegalStateException("Incorrect password — please try again"))
        }
        _currentUser.value = record.first
        persist()
        return Result.success(record.first)
    }

    override suspend fun logOut() {
        _currentUser.value = null
        persist()
    }

    override suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phone: String
    ): Result<LampUser> {
        delay(600) // simulate network
        val current = _currentUser.value
            ?: return Result.failure(IllegalStateException("Not signed in"))
        val updated = current.copy(
            firstName = firstName.trim(),
            lastName = lastName.trim(),
            phone = phone.trim()
        )
        accounts[current.email] = updated to (accounts[current.email]?.second.orEmpty())
        _currentUser.value = updated
        persist()
        return Result.success(updated)
    }

    private fun loadPersisted() {
        val stored = try {
            prefs?.getString(KEY_ACCOUNTS, null)
        } catch (_: Exception) {
            null
        } ?: return
        try {
            val array = JSONArray(stored)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val user = LampUser(
                    firstName = obj.optString("firstName"),
                    lastName = obj.optString("lastName"),
                    email = obj.optString("email").lowercase(),
                    phone = obj.optString("phone")
                )
                if (user.email.isBlank()) continue
                accounts[user.email] = user to obj.optString("password")
            }
            val sessionEmail = try {
                prefs?.getString(KEY_SESSION_EMAIL, null)?.lowercase()
            } catch (_: Exception) {
                null
            }
            sessionEmail?.let { accounts[it]?.first }?.let { _currentUser.value = it }
        } catch (_: Exception) {
            // Corrupt cache: fall back to the seeded in-memory accounts.
        }
    }

    private fun persist() {
        val target = prefs ?: return
        try {
            val array = JSONArray()
            accounts.forEach { (email, record) ->
                val (user, password) = record
                array.put(
                    JSONObject()
                        .put("firstName", user.firstName)
                        .put("lastName", user.lastName)
                        .put("email", email)
                        .put("phone", user.phone)
                        .put("password", password)
                )
            }
            val editor = target.edit().putString(KEY_ACCOUNTS, array.toString())
            val session = _currentUser.value?.email
            if (session != null) editor.putString(KEY_SESSION_EMAIL, session)
            else editor.remove(KEY_SESSION_EMAIL)
            editor.apply()
            Log.d("MockAuth", "persisted: accounts=${accounts.size} session=$session")
        } catch (_: Exception) {
            // Persistence is best-effort; the in-memory session still works.
        }
    }
}
