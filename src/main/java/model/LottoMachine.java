package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.generator.LottoGenerator;

public class LottoMachine {
    private final static Money LOTTO_PRICE = new Money(1000);
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueLotto(Money money) {
        return IntStream.range(0, quantity(money))
                .mapToObj(i -> lottoGenerator.createLotto())
                .collect(Collectors.toList());
    }

    private int quantity(Money money) {
        return money.divide(LOTTO_PRICE).intValue();
    }
}
