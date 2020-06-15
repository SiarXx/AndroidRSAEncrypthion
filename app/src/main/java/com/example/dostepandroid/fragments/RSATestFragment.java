package com.example.dostepandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dostepandroid.R;
import com.example.dostepandroid.tools.RSA;


public class RSATestFragment extends Fragment implements View.OnClickListener {

    RSA rsa;
    private TextView rsaLabel;
    private EditText original;
    private EditText decrypted;
    public RSATestFragment() {
        this.rsa = new RSA();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r_s_a_test, container, false);

        Button check = view.findViewById(R.id.rsaCheckBtn);
        Button encrypt = view.findViewById(R.id.rsaEncryptBtn);
        Button decrypt = view.findViewById(R.id.rsaDecryptBtn);
        check.setOnClickListener(this);
        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);

        rsaLabel = view.findViewById(R.id.rsaLabel);
        original = view.findViewById(R.id.rsaText);
        decrypted = view.findViewById(R.id.rsaDecryptText);

        return view;
    }

    public void rsaCheck(){
        if(rsa.RSAKeysExists()){
            rsaLabel.setText("RSA keys generated correctly");
        }else{
            rsaLabel.setText("RSA keys not generated");
        }
    }
    public void encryptText(){
        if(original.getText().length()>0){
            String encrypthed = rsa.encryptRSAToString(original.getText().toString());
            decrypted.setText(encrypthed);
        }else{
            Toast.makeText(getContext(),"No Text to encrypt",Toast.LENGTH_SHORT).show();
        }
    }
    public void decryptText(){
        if(decrypted.getText().length()>0){
            String decrypthedtext = rsa.decryptRSAToString(decrypted.getText().toString());
            original.setText(decrypthedtext);
        }else if(decrypted.getText().length()==0){
            Toast.makeText(getContext(),"No Text to decrypt",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rsaCheckBtn:
                rsaCheck();
                break;
            case R.id.rsaEncryptBtn:
                encryptText();
                break;
            case R.id.rsaDecryptBtn:
                decryptText();
                break;

        }
    }
}
