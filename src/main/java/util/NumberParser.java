package util;
import domain.Number;

public class NumberParser {

    public static Number parse(String input) {
        return Number.from(NonNegativeIntegerParse.parse(input));
    }
}
