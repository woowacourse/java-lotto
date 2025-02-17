package domain;

import static domain.properties.LottoProperties.COUNT_OF_NUMBERS;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public static Lotto of(final List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers == null || numbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", COUNT_OF_NUMBERS));
        }

        if (containsDuplicate(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean containsDuplicate(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .anyMatch(
                        num1 -> lottoNumbers.stream()
                                .filter(num2 -> num1 != num2)
                                .anyMatch(num2 -> num1.isSameValue(num2))
                );
    }

    public int match(Lotto compared) {
        return (int) numbers.stream()
                .filter(number -> containsSameValue(compared.numbers, number))
                .count();
    }

    public boolean contains(final LottoNumber number) {
        return containsSameValue(this.numbers, number);
    }

    private boolean containsSameValue(List<LottoNumber> numbers, LottoNumber number) {
        return numbers.stream()
                .anyMatch(element -> element.isSameValue(number));
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .toList()
                .toString();
    }
}
