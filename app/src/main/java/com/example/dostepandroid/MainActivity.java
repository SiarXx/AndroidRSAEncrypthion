package com.example.dostepandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dostepandroid.database.AppDatabase;
import com.example.dostepandroid.database.User;
import com.example.dostepandroid.tools.DBWorker;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    public AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        User sample = new User("admin","admin");
        db.userDao().insertUser(sample);
        setContentView(R.layout.activity_main);

    }



}
