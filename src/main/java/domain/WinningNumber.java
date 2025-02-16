package domain;

import exception.LottoException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utility.StringUtility;

public class WinningNumber {

    private static final String DUPLICATE_LOTTO_NUMBERS = "당첨번호는 중복될 수 없습니다!";
    private static final String WINNING_NUMBER_EMPTY = "당첨번호는 공백일 수 없습니다.";
    private static final String WINNING_NUMBER_MUST_BE_NUMBER = "당첨 번호는 숫자여야합니다";
    private static final String WINNING_NUMBER_SIZE_INVALID = "당첨 번호는 6개여야 합니다.";
    private static final int LOTTO_LENGTH = 6;

    private final List<LottoNumber> lottoNumbers;

    public WinningNumber(String inputWinningNumber) {
        validateIsEmpty(inputWinningNumber);
        String[] winningNumbers = StringUtility.removeBlank(inputWinningNumber)
                .split(",");
        validateNumbersValid(winningNumbers);
        List<Integer> parsedWinningNumbers = parsingWinningNumbers(winningNumbers);
        validateDuplication(parsedWinningNumbers);
        lottoNumbers = parsedWinningNumbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private static List<Integer> parsingWinningNumbers(String[] winningNumbers) {
        return Arrays.stream(winningNumbers)
                .map((Integer::parseInt))
                .toList();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(num -> lottoNumber.equals(num));
    }

    private void validateNumbersValid(String[] winningNumbers) {
        validateSizeCheck(winningNumbers);
        Arrays.stream(winningNumbers)
                .forEach(this::validateIsNumber);
    }

    private void validateDuplication(List<Integer> lottoNumbers) {
        Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
        if (lottoNumbers.size() != duplicationSet.size()) {
            throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private static void validateSizeCheck(String[] winningNumbers) {
        if (winningNumbers.length != LOTTO_LENGTH) {
            throw new LottoException(WINNING_NUMBER_SIZE_INVALID);
        }
    }

    private static void validateIsEmpty(String inputWinningNumber) {
        if (inputWinningNumber == null) {
            throw new LottoException(WINNING_NUMBER_EMPTY);
        }
    }

    private void validateIsNumber(String winningNumber) {
        if (!StringUtility.isNumber(winningNumber)) {
            throw new LottoException(WINNING_NUMBER_MUST_BE_NUMBER);
        }
    }
}
