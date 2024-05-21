package com.example.k.Login

import android.content.Context
import androidx.room.Room

class UserRepository(context: Context) {
    private val userDao: UserDao

    init {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "cadastro_pokedex"
        ).build()
        userDao = db.userDao()
    }

    suspend fun insertUser(user: User?) {
        user?.let { userDao.insert(it) }
    }
}

