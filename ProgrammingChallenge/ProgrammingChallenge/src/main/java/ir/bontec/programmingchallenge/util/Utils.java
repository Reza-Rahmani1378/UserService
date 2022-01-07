package ir.bontec.programmingchallenge.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

// generate random Alphabet for userId;
@Component
public class Utils {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "01234556789ABCDEFGHIJKLMNOPQRSTWXYZabcdefghijklmnopqrstwxyz";

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder();

        for (int i = 0; i < length; i++) {
            returnValue.append(
                    ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))
            );
        }
        return new String(returnValue);
    }
}
