package tec.dk.WKPasswordVault.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class encryptionService {
    public static String encrypt(String str) {
        String seed = "secret";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        String encrypted= encryptor.encrypt(str);

        return encrypted;
    }
}
