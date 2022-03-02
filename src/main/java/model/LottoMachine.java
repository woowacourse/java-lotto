package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.generator.LottoGenerator;

public class LottoMachine {
    private final static Budget LOTTO_PRICE = new Budget(1000);
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueLotto(Budget budget) {
        return IntStream.range(0, quantity(budget))
                .mapToObj(i -> lottoGenerator.createLotto())
                .collect(Collectors.toList());
    }

    private int quantity(Budget budget) {
        return budget.divide(LOTTO_PRICE).intValue();
    }
}
