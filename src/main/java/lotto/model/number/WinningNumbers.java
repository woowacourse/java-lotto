package lotto.model.number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;

/*
 * 지난 주 당첨 번호를 List<LottoNumber>로 저장하는 일급 컬렉션 Class
 */
public class WinningNumbers {
    private static final String ERROR_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 당첨 번호는 중복되면 안됩니다";
    private static final int WINNING_NUMBERS_SIZE = 6;

    private final List<LottoNumber> winningNumbers;

    private WinningNumbers(List<LottoNumber> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = List.copyOf(winningNumbers);
    }

    private void validate(List<LottoNumber> winningNumbers) {
        checkSize(winningNumbers);
        checkDuplicate(winningNumbers);
    }

    private void checkSize(List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private void checkDuplicate(List<LottoNumber> winningNumbers) {
        if (getDistinctCount(winningNumbers) != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public static WinningNumbers from(String[] inputs) {
        List<LottoNumber> winningNumbers = Arrays.stream(inputs)
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return new WinningNumbers(winningNumbers);
    }

    private int getDistinctCount(List<LottoNumber> winningNumbers) {
        return (int) winningNumbers.stream()
                .distinct()
                .count();
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.stream()
                .anyMatch(winningNumber -> winningNumber.equals(number));
    }

    public int match(Lotto lotto) {
        return (int) winningNumbers.stream()
                .filter(lotto::contains)
                .count();
    }
}
