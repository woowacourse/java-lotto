package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;

import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int NEED_MATCH_BONUS_COUNT = 5;

    private final List<Integer> numbers;

    public Lotto(List<Integer> inputLotto) {
        validateLength(inputLotto);
        validateLottoRange(inputLotto);
        validateLottoDuplicate(inputLotto);

        this.numbers = inputLotto;
    }

    public int validateBonus(String inputBonus) {
        int bonus = validateIsInteger(inputBonus);
        validateRange(bonus);
        validateBonusDuplicate(bonus);

        return bonus;
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

    private void validateLottoDuplicate(List<Integer> inputLotto) {
        if(inputLotto.stream().distinct().count() != LOTTO_LENGTH){
            throw new IllegalArgumentException(DUPLICATED_NUMBER);
        }
    }

    private void validateLength(List<Integer> inputLotto) {
        if(inputLotto.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }
    
    private void validateLottoRange(List<Integer> inputLotto) {
        for (Integer lottoNum : inputLotto) {
            validateRange(lottoNum);
        }
    }

    private static void validateRange(Integer lottoNum) {
        if(lottoNum < LOTTO_MIN || lottoNum > LOTTO_MAX) {
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

    private void validateBonusDuplicate(int inputBonus) {
        Integer bonus = inputBonus;
        numbers.stream().filter(number -> number.equals(bonus)).forEach(number -> {
            throw new IllegalArgumentException(DUPLICATED_NUMBER);
        });
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
