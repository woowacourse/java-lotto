package domain;

import exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final String DELIMITER = ",";

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(String lotto) {
        numbers = new ArrayList<>();
        String[] split = lotto.split(DELIMITER);
        for (String s : split) {
            int num = validateIsInteger(s);
            validateRange(num);
            numbers.add(num);
        }
    }

    private void validateRange(int num) {
        if(num < LOTTO_MIN || num > LOTTO_MAX){
            throw new IllegalArgumentException(ExceptionMessage.INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(String s) {
        try{
            return Integer.parseInt(s);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
        }
    }
}
