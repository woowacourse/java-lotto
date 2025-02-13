package util;

import java.util.Arrays;
import java.util.stream.Collectors;
import model.Lotto;
import model.Money;
import model.Number;

public class Parser {

    public static Money parseMoney(String rawMoney) {
        return new Money(parseInt(rawMoney));
    }

    public static Lotto parseLotto(String rawInputLotto){
        String[] splittedInput = rawInputLotto.split(", ");
        return new Lotto(Arrays.stream(splittedInput)
                .map(Parser::parseNumber)
                .collect(Collectors.toSet()));
    }

    public static Number parseNumber(String rawNumber){
        return new Number(parseInt(rawNumber));
    }

    public static Integer parseInt(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
