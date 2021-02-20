package lotto.domain.lotto;

import lotto.domain.primitive.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public static Lotto createByInteger(final List<Integer> numbers) {
        return new Lotto(numbers.stream()
                                .map(LottoNumber::new)
                                .collect(Collectors.toList()));
    }

    public Lotto(final List<LottoNumber> numbers) {
        validateNumberCount(numbers);
        validateDistinct(numbers);
        this.numbers = numbers;
    }

    private void validateNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("잘못된 개수의 입력입니다.");
        }
    }

    private void validateDistinct(final List<LottoNumber> numbers) {
        if (numbers.stream()
                   .distinct()
                   .count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자 입력입니다.");
        }
    }

    public int countCommonValue(final Lotto lotto) {
        int totalCount = numbers.size() + lotto.numbers.size();
        Set<LottoNumber> set = new HashSet<>(numbers);
        set.addAll(lotto.numbers);
        return totalCount - set.size();
    }

    public boolean containNumber(final LottoNumber input) {
        return numbers.contains(input);
    }

    public List<Integer> getNumbers() {
        List<LottoNumber> copyNumbers = new ArrayList<>(numbers);
        return Collections.unmodifiableList(copyNumbers.stream()
                                                       .map(LottoNumber::getNumber)
                                                       .sorted()
                                                       .collect(Collectors.toList()));
    }
}
