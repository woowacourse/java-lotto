package domain;

import static global.exception.ExceptionMessage.*;

import domain.dto.GetLottoDto;
import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final String DELIMITER = ",";
    public static final int LOTTO_LENGTH = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(String lotto) {
        numbers = new ArrayList<>();
        String[] splitNumbers = lotto.split(DELIMITER);
        validateLength(splitNumbers);
        for (String number : splitNumbers) {
            int num = validateIsInteger(number.trim());
            validateRange(num);
            numbers.add(num);
        }

        validateLottoDuplicate();
    }

    private void validateLottoDuplicate() {
        if(numbers.stream().distinct().count() != LOTTO_LENGTH){
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateLength(String[] splitNumbers) {
        if(splitNumbers.length != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public int validateBonus(String input) {
        int bonus = validateIsInteger(input);
        validateRange(bonus);
        validateBonusDuplicate(bonus);
        return bonus;
    }

    private void validateBonusDuplicate(int input) {
        Integer bonus = input;
        numbers.stream().filter(number -> number.equals(bonus)).forEach(number -> {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        });
    }

    private void validateRange(int num) {
        if (num < LOTTO_MIN || num > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public GetLottoDto getLottoDto() {
        return new GetLottoDto(numbers);
    }

    public Rank countMatchNumbers(WinningLotto winningLotto) {
        int count = (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
        boolean bonusFlag = false;
        if (count == 5) {
            bonusFlag = winningLotto.matchBonus(numbers);
        }
        return Rank.matchRank(count, bonusFlag);
    }

    protected boolean contains(int number) {
        return numbers.contains(number);
    }


}
