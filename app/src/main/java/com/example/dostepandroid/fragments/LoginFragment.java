package com.example.dostepandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dostepandroid.MainActivity;
import com.example.dostepandroid.R;
import com.example.dostepandroid.database.AppDatabase;
import com.example.dostepandroid.tools.RSA;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText loginfield;
    private EditText passwordfield;
    AppDatabase db;
    RSA rsa = new RSA();
    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main__login, container, false);
        Button LoginBtn = view.findViewById(R.id.LoginBtn);
        loginfield = view.findViewById(R.id.LoginField);
        passwordfield = view.findViewById(R.id.PasswordField);
        LoginBtn.setOnClickListener(this);
        db = ((MainActivity)getActivity()).db;
        return view;
    }

    @Override
    public void onClick(View v) { Login();}

    private void Login() {
        String login = loginfield.getText().toString();
        String password = rsa.encryptRSAToString(passwordfield.getText().toString());
        if(db.userDao().getUser(login,password) == null){
            Toast.makeText(getContext(), "Login or Password Incorrect", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).navigate(R.id.action_main_Login_to_RSATestFragment);
        }
    }

}
