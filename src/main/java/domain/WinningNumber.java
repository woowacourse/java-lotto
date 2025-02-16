package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumber {
    private static final String DELIMITER = ", ";

    private final List<Integer> numbers = new ArrayList<>();

    public WinningNumber(String winningNumberInput) {
        String[] winningNumbers = winningNumberInput.split(DELIMITER);
        validateNumberCount(winningNumbers);

        for (String winningNumber : winningNumbers) {
            validateNumber(winningNumber);
            numbers.add(Integer.parseInt(winningNumber));
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int findMatchingCountWith(List<Integer> lottoNumbers) {
        return (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    private void validateNumberCount(String[] winningNumbers) {
        if (winningNumbers.length != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호를 \", \"로 구분된 6개의 정수로 입력해주세요.");
        }
    }

    private void validateNumber(String winningNumber) {
        try {
            int number = Integer.parseInt(winningNumber);
            throwRangeException(number);
            throwDuplicateException(number);
        } catch (NumberFormatException e) {
            throwDigitException(winningNumber);
            throw new IllegalArgumentException("당첨 번호는 1~45 사이의 정수로 입력해주세요.");
        }
    }

    private void throwRangeException(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("당첨 번호는 1~45 사이의 정수로 입력해주세요.");
        }
    }

    private void throwDuplicateException(int number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException("당첨 번호 간 중복 없이 입력해주세요.");
        }
    }

    private void throwDigitException(String winningNumber) {
        if (winningNumber.length() > 10) {
            throw new IllegalArgumentException("당첨 번호는 10자리 이하의 정수로 입력해주세요.");
        }
    }
}
