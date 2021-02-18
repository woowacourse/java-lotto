package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String NON_NUMERIC_ERROR = "숫자만 입력 가능합니다.";
    private static final String NOT_SIX_NUMBERS_ERROR = "당첨 번호는 총 6개 이어야 합니다.";
    private static final String NUMBER_OUT_OF_BOUNDS_ERROR = "1 에서 45 사이의 숫자만 입력해주세요.";
    private static final String DUPLICATE_ERROR = "중복되는 번호는 안됩니다.";

    private final List<Integer> winningLottoNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLottoNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningLotto(String[] winningNumbers, String bonusNumber) {
        validateNumbers(winningNumbers);
        this.winningLottoNumbers = createWinningLotto(winningNumbers);

        validateBonusNumber(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    private List<Integer> createWinningLotto(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validateBonusNumber(String bonusNumber) {
        validateNumeric(bonusNumber);
        validateNumberBetween1to45(bonusNumber);
        validateDuplicatesWithWinningNumbers(bonusNumber);
    }

    private void validateNumbers(String[] numbers) {
        for (String number : numbers) {
            validateNumeric(number);
            validateNumberBetween1to45(number);
        }
        validateSixNumbers(numbers);
        validateDuplicates(numbers);
    }

    private void validateNumeric(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR);
        }
    }

    private void validateSixNumbers(String[] numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException(NOT_SIX_NUMBERS_ERROR);
        }
    }

    private void validateNumberBetween1to45(String number) {
        if (Integer.parseInt(number) < 1 && 45 < Integer.parseInt(number)) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_BOUNDS_ERROR);
        }
    }

    private void validateDuplicates(String[] numbers) {
        Set<String> numberGroup = new HashSet<>(Arrays.asList(numbers));
        if (numberGroup.size() != 6) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private void validateDuplicatesWithWinningNumbers(String number) {
        if (winningLottoNumbers.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getWinningLottoNumbers() {
        return Collections.unmodifiableList(winningLottoNumbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
