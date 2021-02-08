package com.ghostCoder.invoicePro.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHash {
    private String hashedPassword;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //generate salt
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = f.generateSecret(keySpec).getEncoded();

        Base64.Encoder enc = Base64.getEncoder();

        setHashedPassword(enc.encodeToString(hash));
        setSalt(enc.encodeToString(salt));
    }

    public boolean comparePassword(String password){
        if(password.equals(hashedPassword)){
            return true;
        }else{
            return false;
        }
    }

    public String getHash(String password, String strSalt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //generate salt
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] salt = decoder.decode(strSalt);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = f.generateSecret(keySpec).getEncoded();

        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(hash);
    }
}
