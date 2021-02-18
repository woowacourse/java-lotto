package lottogame.domain.lotto;

import lottogame.utils.InvalidWinningLottoException;
import lottogame.utils.RedundantNumbersException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN =
            Pattern.compile("^\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2},\\s\\d{1,2}$");
    private static final Pattern BONUS_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(String numbers, String bonusNumber) {
        List<Integer> lotto = split(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
        isDuplicate(lotto, this.bonusNumber);
        this.lotto = new Lotto(lotto);    // 당첨 번호 로또 생성
    }

    private List<Integer> split(String numbers) {
        validateInput(numbers);
        String[] lotto = numbers.split(DELIMITER);
        return Arrays.stream(lotto)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validateBonusNumber(String bonusNumber) {
        if (!BONUS_NUMBER_PATTERN.matcher(bonusNumber).matches()) {
            throw new InvalidWinningLottoException();
        }
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

    public Integer getBonusBall() {
        return new Integer(this.bonusNumber);
    }

    public Lotto values() {
        return lotto;
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
