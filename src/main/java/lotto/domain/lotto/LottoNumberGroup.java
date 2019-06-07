package lotto.domain.lotto;

import lotto.util.StringConverter;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumberGroup {
    static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    private LottoNumberGroup(List<LottoNumber> numbers) {
        this.numbers = Collections.unmodifiableSet(new TreeSet<>(numbers));
        checkDuplicate(numbers);
        checkLottoSize();
    }

    private void checkDuplicate(List<LottoNumber> numbers) {
        if (numbers.size() != this.numbers.size()) {
            throw new InvalidLottoNumberGroupException("로또 번호는 중복이 불가능합니다.");
        }
    }

    private void checkLottoSize() {
        if (this.numbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoNumberGroupException("로또 번호는 6개의 숫자로 구성되어야합니다.");
        }
    }

    public static LottoNumberGroup create(String numbersText) {
        try {
            return create(StringConverter.toNumbers(numbersText));
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumberGroupException("로또 번호는 숫자로 입력하세요");
        }
    }

    private static LottoNumberGroup create(List<Integer> lottoNumbers) {
        try {
            return new LottoNumberGroup(LottoNumbersGenerator.generate(lottoNumbers));
        } catch (InvalidLottoNumberException e) {
            throw new InvalidLottoNumberGroupException(e.getMessage());
        }
    }

    public static LottoNumberGroup create() {
        return new LottoNumberGroup(LottoNumbersGenerator.generate());
    }

    public int countOfMatch(LottoNumberGroup lottoNumbers) {
        return (int) lottoNumbers.numbers.stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public LottoNumberGroup clone() {
        return new LottoNumberGroup(
                numbers.stream()
                .collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumberGroup that = (LottoNumberGroup) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
