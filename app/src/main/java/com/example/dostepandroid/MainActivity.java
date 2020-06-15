package com.example.dostepandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dostepandroid.database.AppDatabase;
import com.example.dostepandroid.database.User;
import com.example.dostepandroid.tools.DBWorker;
import com.example.dostepandroid.tools.RSA;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    public AppDatabase db;
    SharedPreferences prefs = null;
    RSA rsa = new RSA();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("com.example.databasemodule",MODE_PRIVATE);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        User sample = new User("admin","admin");
        db.userDao().insertUser(sample);
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onResume(){
        super.onResume();
        //Sprawdzenie czy aplikacja uruchamiana jest pierwszy raz
        IsFirstRun();
    }
    private void IsFirstRun() {
        if(prefs.getBoolean("firstrun",true)){

            //Dodaj domyslnego użytkownika na start aplikacji
            User Admin = new User();
            Admin.Login = "admin";

            //przykład użycia RSA do zaszyfrowania hasła
            Admin.Password = rsa.encryptRSAToString("ZAQ!2wsx");


            prefs.edit().putBoolean("firstrun",false).commit();
        }
    }



}
