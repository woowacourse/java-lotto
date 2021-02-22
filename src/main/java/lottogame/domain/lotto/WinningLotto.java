package lottogame.domain.lotto;

import lottogame.utils.InvalidWinningLottoException;
import lottogame.utils.RedundantNumbersException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");
    private static final Pattern BONUS_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(String numbers, String bonusNumber) {
        List<LottoNumber> lotto = split(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = new LottoNumber(Integer.parseInt(bonusNumber));
        isDuplicate(lotto, this.bonusNumber);
        this.lotto = new Lotto(lotto);
    }

    private List<LottoNumber> split(String numbers) {
        validateInput(numbers);
        String[] lotto = numbers.split(DELIMITER);
        return Arrays.stream(lotto)
                .map(number -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList());
    }

    private void validateInput(String numbers) {
        if (!NUMBER_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    private void validateBonusNumber(String bonusNumber) {
        if (!BONUS_NUMBER_PATTERN.matcher(bonusNumber).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    private void isDuplicate(List<LottoNumber> lotto, LottoNumber bonusNumber) {
        Set<LottoNumber> numbers = new HashSet<>(lotto);
        if (numbers.size() != lotto.size() || lotto.contains(bonusNumber)) {
            throw new RedundantNumbersException();
        }
    }

    public LottoNumber getBonusBall() {
        return this.bonusNumber;
    }

    public Lotto values() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }
}
