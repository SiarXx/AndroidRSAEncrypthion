package com.example.dostepandroid.tools;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

///This is Class Short Description
///
///This is Class Long Description and more
public class RSA {

    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String alias = "RSAAlias";


    public RSA() {
        if(RSAKeysExists()){
            loadKeys();
            this.keyPair = null;
        }
        else{
           this.keyPair = getKeyPair();
           loadKeys();
        }
    }

    private KeyPair getKeyPair(){
        KeyPair kp = null;
        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA,"AndroidKeyStore");
            kpg.initialize(new KeyGenParameterSpec.Builder(
                            alias,
                            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setDigests(KeyProperties.DIGEST_SHA256,KeyProperties.DIGEST_SHA512)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setRandomizedEncryptionRequired(false)
                    .setKeySize(2048)
                    .build()
            );
            kp = kpg.generateKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
        return kp;
    }


    //load keys from keystore and inform if finished successfully
    private boolean loadKeys(){
        try {
            KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
            ks.load(null);
            KeyStore.Entry entry = ks.getEntry(alias,null);
            this.privateKey = ((KeyStore.PrivateKeyEntry)entry).getPrivateKey();
            this.publicKey = ks.getCertificate(alias).getPublicKey();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public String encryptRSAToString(String clearText) {
        String encryptedBase64 = "";
        try {
            Key key = this.publicKey;

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
            encryptedBase64 = new String(Base64.encode(encryptedBytes, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedBase64.replaceAll("(\\r|\\n)", "");
    }

    public String decryptRSAToString(String encryptedBase64) {

        String decryptedString = "";
        try {

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            // encrypt the plain text using the public key
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);

            byte[] encryptedBytes = Base64.decode(encryptedBase64, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedString;
    }
    public boolean RSAKeysExists(){
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance("AndroidKeyStore");
            ks.load(null);
            return (ks.containsAlias(alias));
        } catch (Exception e) {
            return false;
        }

    }

}
