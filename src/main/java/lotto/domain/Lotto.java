package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.util.LottoGenerator;

public class Lotto {
    public static final int PRICE = 1_000;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public   Lotto(LottoGenerator generator) {
        this.numbers = generator.generate(LOTTO_NUMBER_COUNT).stream()
                .sorted()
                .toList();
    }

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
            .map(LottoNumber::of)
            .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d개를 입력해야 합니다.", LOTTO_NUMBER_COUNT));
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    public int findMatchCount(Lotto lotto) {
        return (int) IntStream.range(0, numbers.size())
            .filter(i -> lotto.numbers.contains(numbers.get(i)))
            .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
