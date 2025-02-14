package lotto.utils;

import static lotto.exception.ErrorMessage.INVALID_INPUT;

import java.util.List;
import lotto.exception.LottoException;

public class Splitter {

    public static List<String> splitByComma(String input) {

        String regex = "\\s*\\d+\\s*(,\\s*\\d+\\s*)*";
        try {
            if (!input.matches(regex)) {
                throw new LottoException(INVALID_INPUT);
            }
            return List.of(input.split(","));
        } catch (NumberFormatException e) {
            throw new LottoException(INVALID_INPUT);
        }
    }
}
