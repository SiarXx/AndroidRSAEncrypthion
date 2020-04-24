package com.example.dostepandroid.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = @Index(value = "Login", unique = true))
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="Id")
    public int ID;

    @ColumnInfo(name = "Login")
    public String Login;

    @ColumnInfo(name = "Password")
    public String Password;

    public User(@NonNull String login,@NonNull String password){
        this.Login = login;
        this.Password = password;
    }
    public User(){}
}
