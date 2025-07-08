package com.bankexample.cardmanagementsystem.security;

import com.bankexample.cardmanagementsystem.exception.EncryptionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionCardUtil {

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public EncryptionCardUtil(@Value("${security.encryption-card-util.secret-key}") String secretKey) throws Exception {
        if (secretKey == null || secretKey.length() != 16) {
            throw new IllegalArgumentException("Secret key must be 16 characters long");
        }

        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");

        this.encryptCipher = Cipher.getInstance("AES");
        this.encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        this.decryptCipher = Cipher.getInstance("AES");
        this.decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String plainText) {
        try {
            byte[] encrypted = encryptCipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new EncryptionException("Ошибка при шифровании номера карты", e);
        }
    }

    public String decrypt(String encryptedText) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encryptedText);
            byte[] decrypted = decryptCipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            throw new EncryptionException("Ошибка при дешифровании номера карты", e);
        }
    }
}
