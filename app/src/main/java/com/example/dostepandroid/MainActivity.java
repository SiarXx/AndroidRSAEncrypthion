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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppDatabase db;
    EditText loginfield;
    EditText passwordfield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        DBWorker worker = new DBWorker("dbWork");
        User sample = new User("admin","admin");
        db.userDao().insertUser(sample);
        setContentView(R.layout.activity_main);
        Button LoginBtn = findViewById(R.id.LoginBtn);
        loginfield = findViewById(R.id.LoginField);
        passwordfield = findViewById(R.id.PasswordField);
        LoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Login();
    }

    private void Login() {
        String login = loginfield.getText().toString();
        String password = passwordfield.getText().toString();
        if(db.userDao().getUser(login,password) == null){
            Toast.makeText(this, "Login or Password Incorrect", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
        }
    }
}
