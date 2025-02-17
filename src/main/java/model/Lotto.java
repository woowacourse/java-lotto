package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(NumberGenerator numberGenerator) {
        this.numbers = generateLotto(numberGenerator);
    }

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers.stream().map(LottoNumber::new).toList();
    }

    public Integer countMatchNumber(Lotto otherNumbers) {
        List<LottoNumber> matchList = otherNumbers.getNumbers().stream()
                .filter(otherNumber -> numbers.stream().anyMatch((number) -> number.equals(otherNumber)))
                .toList();
        return matchList.size();
    }

    public Boolean bonusMatch(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    private List<LottoNumber> generateLotto(NumberGenerator numberGenerator) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(new LottoNumber(numberGenerator.generate()));
        }
        return lottoNumbers.stream().sorted().toList();
    }

    private void validateNumberCount(List<Integer> winNumbers) {
        if (winNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException("중복이 아닌 숫자를 입력해주세요");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }
}
