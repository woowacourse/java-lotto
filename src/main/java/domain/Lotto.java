package domain;

import static global.exception.ExceptionMessage.*;

import domain.dto.LottoResponse;
import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int NEED_MATCH_BONUS_COUNT = 5;
    public static final String DELIMITER = ",";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(String inputLotto) {
        numbers = new ArrayList<>();
        String[] splitNumbers = inputLotto.split(DELIMITER);
        validateLength(splitNumbers);
        
        for (String number : splitNumbers) {
            int validatedNum = validateIsInteger(number.trim());
            validateRange(validatedNum);
            numbers.add(validatedNum);
        }

        validateLottoDuplicate();
    }

    public int validateBonus(String inputBonus) {
        int bonus = validateIsInteger(inputBonus);
        validateRange(bonus);
        validateBonusDuplicate(bonus);

        return bonus;
    }

    public LottoResponse getLottoDto() {
        return new LottoResponse(numbers);
    }

    public Rank countMatchNumbers(WinningLotto winningLotto) {
        int count = (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();

        boolean isBonusMatched = false;
        if (count == NEED_MATCH_BONUS_COUNT) {
            isBonusMatched = winningLotto.matchBonus(numbers);
        }

        return Rank.matchRank(count, isBonusMatched);
    }

    protected boolean contains(int number) {
        return numbers.contains(number);
    }

    private void validateLottoDuplicate() {
        if(numbers.stream().distinct().count() != LOTTO_LENGTH){
            throw new IllegalArgumentException(DUPLICATED_NUMBER);
        }
    }

    private void validateLength(String[] splitNumbers) {
        if(splitNumbers.length != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }

    private void validateBonusDuplicate(int inputBonus) {
        Integer bonus = inputBonus;
        numbers.stream().filter(number -> number.equals(bonus)).forEach(number -> {
            throw new IllegalArgumentException(DUPLICATED_NUMBER);
        });
    }

    private void validateRange(int lottoNum) {
        if (lottoNum < LOTTO_MIN || lottoNum > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private int validateIsInteger(String lottoNum) {
        try {
            return Integer.parseInt(lottoNum);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }

}
