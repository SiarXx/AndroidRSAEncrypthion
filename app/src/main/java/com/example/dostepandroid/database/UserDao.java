package com.example.dostepandroid.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE login LIKE :login AND password LIKE :password")
    User getUser(String login, String password);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);
}
