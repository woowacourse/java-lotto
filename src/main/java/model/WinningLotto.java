package model;

import static model.Lotto.LOTTO_SIZE;
import static model.Lotto.MAXIMUM_LOTTO_NUMBER;
import static model.Lotto.MINIMUM_LOTTO_NUMBER;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {

    public static final String NUMBER_REGEX = "^[0-9]*[0-9]$";
    public static final String WINNING_NUMBERS_INPUT_ERROR_MESSAGE = "당첨 번호는 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    public static final String BONUS_INPUT_ERROR_MESSAGE = "보너스볼은 당첨 번호와 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    public static final int MAXIMUM_UNION_SIZE = 12;

    private final List<Integer> winningNumbers = new ArrayList<>();
    private int bonus;

    public WinningLotto(String rawWinningNumbers) {
        Arrays.stream(rawWinningNumbers.split(","))
                .map(this::getWinningNumber)
                .forEach(winningNumbers::add);
        validateDuplicateWinningNumbers();
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(String rawBonus) {
        rawBonus = rawBonus.trim();
        validateBonus(rawBonus);
        bonus = Integer.parseInt(rawBonus);
    }

    public int getDuplicateNumber(LottoDto lottoDto) {
        Set<Integer> union = new HashSet<>(winningNumbers);
        union.addAll(lottoDto.lotto());
        return MAXIMUM_UNION_SIZE - union.size();
    }

    private void validateBonus(String rawBonus) {
        if (!isNumber(rawBonus)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }
        int bonus = Integer.parseInt(rawBonus);
        if (!isValidateNumberRange(bonus)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }

        if (isDuplicateWithWinningNumbers(bonus)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateWithWinningNumbers(int bonus) {
        return winningNumbers.contains(bonus);
    }

    private int getWinningNumber(String rawNumber) {
        rawNumber = rawNumber.trim();
        validateWinningNumber(rawNumber);
        return Integer.parseInt(rawNumber);
    }

    private void validateWinningNumber(String rawNumber) {
        if (!isNumber(rawNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }

        int number = Integer.parseInt(rawNumber);
        if (!isValidateNumberRange(number)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateWinningNumbers() {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }
    }

    private boolean isNumber(String rawNumber) {
        return rawNumber.matches(NUMBER_REGEX);
    }

    private boolean isValidateNumberRange(int number) {
        return number >= MINIMUM_LOTTO_NUMBER && number <= MAXIMUM_LOTTO_NUMBER;
    }

}
