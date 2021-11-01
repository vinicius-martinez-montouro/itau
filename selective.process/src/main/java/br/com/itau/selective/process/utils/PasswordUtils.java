package br.com.itau.selective.process.utils;

import lombok.experimental.UtilityClass;

import static br.com.itau.selective.process.utils.StringUtils.EMPTY;
import static br.com.itau.selective.process.utils.StringUtils.SPACE;

@UtilityClass
public class PasswordUtils {

    public static boolean isSizeBiggerThanOne(String value) {
        return value == null || value.isEmpty() || value.length() < 1;
    }

    public static boolean hasOneUpperCharacter(String value) {
        return value.matches(".*[A-Z].*");
    }

    public static boolean hasOneLowerCharacter(String value) {
        return value.matches(".*[a-z].*");
    }

    public static boolean hasOneSpecialCharacter(String value) {
        return value.matches(".*[^a-zA-Z0-9].*");
    }

    public static boolean hasNotSpaceInString(String value) {
        return value.length() > value.replace(SPACE, EMPTY).length();
    }

    public static boolean hasRepeatCharacter(String value) {
        for (char character : value.toCharArray())
            if (value.replace(String.valueOf(character), EMPTY).length() < (value.length() - 1))
                return true;
        return false;
    }

}
