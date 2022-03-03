package lotto.model.lotto;

import java.util.*;
import java.util.stream.Collectors;

import lotto.model.message.LottoNumberExceptionMessage;
import lotto.model.result.Rank;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLottoResponse;
import lotto.utils.InputValidateUtils;

public class Lotto {
    private static final int CHECKING_BONUS_NUMBER = 5;
    private static final int WINNING_NUMBER_SIZE = 6;
    private static final String CONCAT = "";

    private final Set<Integer> numbers;

    public Lotto(List<String> numbers) {
        validateNumbers(numbers);
        this.numbers = makeWinningToNumbers(numbers);
    }

    private void validateNumbers(List<String> numbers) {
        inputBlank(numbers);
        InputValidateUtils.inputNumber(makeNumbersToString(numbers), LottoNumberExceptionMessage.NUMBER_ERROR.getMassage());
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
    }

    private void inputBlank(List<String> numbers) {
        if (numbers == null || isBlankNumbers(numbers)) {
            throw new IllegalArgumentException(LottoNumberExceptionMessage.BLANK_ERROR.getMassage());
        }
    }

    private boolean isBlankNumbers(List<String> numbers) {
        return numbers.stream()
                .anyMatch(String::isBlank);
    }

    private String makeNumbersToString(List<String> numbers) {
        return String.join(CONCAT, numbers);
    }

    private void validateNumberOutOfRange(List<String> numbers) {
        numbers.forEach(number ->
                InputValidateUtils.inputOutOfRange(number, LottoNumberExceptionMessage.RANGE_ERROR.getMassage()));
    }

    private void validateNumberSize(List<String> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(LottoNumberExceptionMessage.SIZE_ERROR.getMassage());
        }
    }

    private void validateNumberReduplication(List<String> numbers) {
        long count = numbers.stream()
                .distinct()
                .count();

        if (count != numbers.size()) {
            throw new IllegalArgumentException(LottoNumberExceptionMessage.REDUPLICATION_ERROR.getMassage());
        }
    }

    private Set<Integer> makeWinningToNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void calcWinningNumber(WinningResult winningResult, WinningLottoResponse winningLottoResponse) {
        Set<Integer> winningNumbers = winningLottoResponse.getWinningNumbers();
        long count = countWinningNumber(winningNumbers);

        if (count == CHECKING_BONUS_NUMBER) {
            compareWithBonusAndStore(winningResult, winningLottoResponse);
            return;
        }
        storeResult(winningResult, count);
    }

    private long countWinningNumber(Set<Integer> winningNumbers) {
        return numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void compareWithBonusAndStore(WinningResult winningResult, WinningLottoResponse winningLottoResponse) {
        if (numbers.contains(winningLottoResponse.getBonusBall())) {
            winningResult.addCount(Rank.BONUS);
            return;
        }
        winningResult.addCount(Rank.FIVE);
    }

    private void storeResult(WinningResult winningResult, long count) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatchNumber(count))
                .forEach(winningResult::addCount);
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
