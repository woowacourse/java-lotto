package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottoes issueLotto(Money money) {
        return new Lottoes(createLottoes(money));
    }

    private List<Lotto> createLottoes(Money money) {
        return IntStream.range(0, quantity(money))
            .mapToObj(i -> lottoGenerator.createLotto())
            .collect(toUnmodifiableList());
    }

    private int quantity(Money money) {
        return money.divide(Lotto.PRICE).intValue();
    }
}
