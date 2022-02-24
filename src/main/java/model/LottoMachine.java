package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private final static Money LOTTO_PRICE = new Money(1000);
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoMachine(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public List<LottoNumbers> issueLotto(Money money) {
        return IntStream.range(0, quantity(money))
                .mapToObj(i -> lottoNumbersGenerator.createLottoNumbers())
                .collect(Collectors.toList());
    }

    private int quantity(Money money) {
        return money.divide(LOTTO_PRICE).intValue();
    }
}
