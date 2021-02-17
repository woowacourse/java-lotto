package lotto.domain.lottoData;

import lotto.utils.InvalidWinningLottoException;
import lotto.utils.RedundantNumbersException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN =
            Pattern.compile("^\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2}$");
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(String numbers, int bonusNumber) {
        List<Integer> lotto = split(numbers);
        isDuplicate(lotto, bonusNumber);
        this.lotto = new Lotto(lotto);    // 당첨 번호 로또 생성
        this.bonusNumber = bonusNumber;
    }

    private List<Integer> split(String numbers) {
        validateInput(numbers);
        String[] lotto = numbers.split(DELIMITER);
        return Arrays.stream(lotto)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validateInput(String numbers) {
        if (!NUMBER_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    private void isDuplicate(List<Integer> lotto, int bonusNumber) {
        Set<Integer> numbers = new HashSet<>(lotto);
        if (numbers.size() != lotto.size() || lotto.contains(bonusNumber)) {
            throw new RedundantNumbersException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonusNumber == that.bonusNumber && Objects.equals(lotto, that.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }
}
