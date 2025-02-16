package util;

import java.util.Arrays;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;
import model.Money;

public class Parser {

    private static final String DELIMITER = ", ";

    public static Money parseMoney(String rawMoney) {
        return new Money(parseInt(rawMoney));
    }

    public static Lotto parseLotto(String rawInputLotto) {
        String[] splittedInput = rawInputLotto.split(DELIMITER);
        return new Lotto(Arrays.stream(splittedInput)
                .map(Parser::parseNumber)
                .collect(Collectors.toSet()));
    }

    public static LottoNumber parseNumber(String rawNumber) {
        return new LottoNumber(parseInt(rawNumber));
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
