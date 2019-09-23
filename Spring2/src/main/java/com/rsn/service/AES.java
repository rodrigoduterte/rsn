package com.rsn.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Gabriel Ferrer
 * is a class that performs Encryption and Decryption
 *
 */


@Service()
@PropertySource("classpath:app.properties")
public class AES {
 
    private SecretKeySpec secretKey;
    private byte[] key;
    @Value("${aes_key}")
    private String secret; 
    
    
    public void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            this.key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            this.key = sha.digest(key);
            this.key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public String encrypt(String strToEncrypt)
    {
        try
        {
            setKey(this.secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public String decrypt(String strToDecrypt)
    {
        try
        {
            setKey(this.secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    public static void main(String[] args) {
    	final String secretKey = System.getenv("aes_key");
        String originalString = "ugfjydfthf564";
        
        AES aes = new AES();
        String encryptedString = aes.encrypt(originalString) ;
        String decryptedString = aes.decrypt(encryptedString) ;
         
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
	}
}