package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final Money PRICE = new Money(1000);

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public static int countPurchasableLottosByMoney(Money money) {
        if (money.getValue() % PRICE.getValue() != 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d원 단위만 가능합니다.", PRICE.getValue()));
        }
        return money.getValue() / PRICE.getValue();
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 구성돼야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또는 서로 다른 숫자로 구성돼야 합니다.");
        }
    }
}
