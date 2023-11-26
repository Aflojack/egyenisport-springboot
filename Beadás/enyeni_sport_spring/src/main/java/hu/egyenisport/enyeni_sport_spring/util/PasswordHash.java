package hu.egyenisport.enyeni_sport_spring.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class PasswordHash {
    public static String getHashedPassword(String password){
        return toHexString(getSHA(password));
    }

    public static boolean isSamePassword(String password, String hashedPassword){
        String newHashedPassword=getHashedPassword(password);
        return newHashedPassword.equals(hashedPassword);
    }

    private static byte[] getSHA(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch(NoSuchAlgorithmException e){
            return null;
        }
    }

    private static String toHexString(byte[] hash) {
        if(hash==null){
            return "";
        }
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
