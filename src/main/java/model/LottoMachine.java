package model;

import generator.NumberGenerator;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int START_INDEX = 0;
    private final NumberGenerator numberGenerator;

    public LottoMachine(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final PurchaseAmount purchaseAmount) {
        return IntStream.range(START_INDEX, purchaseAmount.calculateLottoCount())
                .mapToObj(index -> generateRandomLotto())
                .toList();
    }

    private Lotto generateRandomLotto() {
        return Lotto.from(numberGenerator.generateNumbers(Lotto.LOTTO_NUMBER_COUNT));
    }
}
