package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int COUNT = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static LottoTicket createTicket(NumberGenerator generator) {
        return new LottoTicket(
            generator.generate(COUNT)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList())
        );
    }

    private void validate(List<LottoNumber> numbers) {
        validateLength(numbers);
        validateDistinct(numbers);
    }

    private void validateDistinct(List<LottoNumber> numbers) {
        if (!isDistinct(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean isDistinct(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() == COUNT;
    }

    private void validateLength(List<LottoNumber> numbers) {
        if (numbers.size() != COUNT) {
            throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d 이어야 합니다.", COUNT));
        }
    }
}
