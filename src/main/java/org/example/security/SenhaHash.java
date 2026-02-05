package org.example.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaHash {

    public static String gerarHash(String senha){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();
            for(byte b : hashBytes){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Erro ao geral hash", e);
        }
    }
}
