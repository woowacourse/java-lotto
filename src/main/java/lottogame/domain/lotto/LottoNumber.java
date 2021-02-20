package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;
import lottogame.utils.InvalidBonusBallNumberException;
import lottogame.utils.InvalidLottoNumberRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_NUMBER_VOLUME = 6;
    private final List<Integer> numbers = new ArrayList<>();

    public LottoNumber() {
        for (int i = 0; i < LOTTO_NUMBER_VOLUME; i++) {
            numbers.add(makeNumber());
        }
        sortNumbers();
    }

    public LottoNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers.addAll(numbers);
        sortNumbers();
    }

    private void sortNumbers() {
        Collections.sort(numbers);
    }

    public LottoNumber(int bonusBall) {
        if (invalidRange(bonusBall)) {
            throw new InvalidBonusBallNumberException();
        }
        numbers.add(bonusBall);
    }

    private void validate(List<Integer> numbers) {
        validNumberRange(numbers);
        duplicateNumbers(numbers);
    }

    private void validNumberRange(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> invalidRange(number))
                .findAny()
                .ifPresent(s -> {
                    throw new InvalidLottoNumberRangeException();
                });
    }

    private boolean invalidRange(int number) {
        return number < LOTTO_MIN || number > LOTTO_MAX;
    }

    private void duplicateNumbers(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new DuplicateLottoNumberException();
        }
    }

    private Integer makeNumber() {
        int number = LottoNumberGenerator.generate(LOTTO_MIN, LOTTO_MAX);
        if (numbers.contains(number)) {
            return makeNumber();
        }
        return number;
    }

    public List<Integer> values() {
        return Collections.unmodifiableList(numbers);
    }

    public int matchCount(WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(number -> winningLotto.contains(number))
                .count();
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public boolean contains(LottoNumber bonusBall) {
        return numbers.stream()
                .filter(number -> bonusBall.contains(number))
                .findFirst()
                .isPresent();
    }
}
