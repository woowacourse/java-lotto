package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.generator.LottoGenerator;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueLotto(Budget budget) {
        return IntStream.range(0, budget.getAffordableLottoCount())
                .mapToObj(i -> lottoGenerator.createLotto())
                .collect(Collectors.toList());
    }
}
