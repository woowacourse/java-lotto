package domain;

import domain.dto.GetLottoDto;
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

    public int validateBonus(String input) {
        int bonus = validateIsInteger(input);
        validateRange(bonus);
        validateIsDuplicate(bonus);
        return bonus;
    }

    private void validateIsDuplicate(int input) {
        Integer bonus = Integer.valueOf(input);
        numbers.stream().filter(number -> number.equals(bonus)).forEach(number -> {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBER.getMessage());
        });
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

    public GetLottoDto getLottoDto() {
        return new GetLottoDto(numbers);
    }

    public int countMatchNumbers(Lotto other) {
        return (int) numbers.stream()
                .filter(other::contains)
                .count();
    }

    private boolean contains(int number) {
        return numbers.contains(number);
    }

}
