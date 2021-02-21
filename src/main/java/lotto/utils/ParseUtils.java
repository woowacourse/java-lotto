package lotto.utils;

import lotto.exception.IllegalTypeException;

public class ParseUtils {

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalTypeException();
        }
    }
}
