package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers {
    private static final String ERROR_NOT_MATCH_WINNING_NUMBER_SIZE = "지난 주 당첨 번호 개수는 6개로 입력해주세요.";
    private static final String ERROR_OUT_OF_RANGE_WINNING_NUMBERS = "당첨 번호의 범위는 1 ~ 45 사이로 입력해주세요.";
    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호에 중복이 존재합니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validateNumberOfWinningNumbers(winningNumbers);
        validateDuplicationWinningNumbers(winningNumbers);
        validateRangeWinningNumbers(winningNumbers);
        this.winningNumbers = convertIntegersToLottoNumbers(winningNumbers);
    }

    private List<LottoNumber> convertIntegersToLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateNumberOfWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_WINNING_NUMBER_SIZE);
        }
    }

    private void validateRangeWinningNumbers(List<Integer> winningNumbers) {
        winningNumbers.forEach(number -> {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_WINNING_NUMBERS);
            }
        });
    }

    private void validateDuplicationWinningNumbers(List<Integer> winningNumbers) {
        Set<Integer> distinct = new HashSet<>(winningNumbers);
        if (distinct.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    public boolean contains(Object number) {
        return winningNumbers.contains(number);
    }
}
