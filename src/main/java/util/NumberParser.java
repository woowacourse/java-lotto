package util;
import domain.Number;

public class NumberParser {

    public static Number parse(String input) {
        int integer = Integer.parseInt(input);

        return Number.from(integer);
    }
}
