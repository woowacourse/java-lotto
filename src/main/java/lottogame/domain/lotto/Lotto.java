package lottogame.domain.lotto;

import lottogame.utils.InvalidWinningLottoException;
import lottogame.utils.RedundantNumbersException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lotto {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> values) {
        numbers = values;
    }

    public static Lotto of(String lottoNumber) {
        List<LottoNumber> lotto = split(lottoNumber);
        isDuplicate(lotto);
        return new Lotto(lotto);
    }

    private static List<LottoNumber> split(String numbers) {
        validateInput(numbers);
        String[] lotto = numbers.split(DELIMITER);
        return Arrays.stream(lotto)
                .map(number -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList());
    }

    private static void isDuplicate(List<LottoNumber> lotto) {
        Set<LottoNumber> numbers = new HashSet<>(lotto);
        if (numbers.size() != lotto.size()) {
            throw new RedundantNumbersException();
        }
    }

    private static void validateInput(String numbers) {
        if (!NUMBER_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    public List<LottoNumber> values() {
        return new ArrayList<>(numbers);
    }

    public int match(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(number -> winningLotto.contains(number))
                .count();
    }

    public boolean contains(LottoNumber lottonumber) {
        return numbers.contains(lottonumber);
    }

    public boolean containsBonus(WinningLotto winningLotto) {
        return numbers.contains(winningLotto.getBonusBall());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
