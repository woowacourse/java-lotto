package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final static Money LOTTO_PRICE = new Money(1000);
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueLotto(Money money) {
        return IntStream.range(0, quantity(money))
            .mapToObj(i -> lottoGenerator.createLotto())
            .collect(toUnmodifiableList());
    }

    private int quantity(Money money) {
        return money.divide(LOTTO_PRICE).intValue();
    }
}
