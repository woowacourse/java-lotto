package model;

import generator.RandomNumberGenerator;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int START_INDEX = 0;
    private final RandomNumberGenerator numberGenerator;

    public LottoMachine(final RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final PurchaseAmount purchaseAmount) {
        return IntStream.range(START_INDEX, purchaseAmount.calculateLottoCount())
                .mapToObj(index -> generateRandomLotto())
                .toList();
    }

    private Lotto generateRandomLotto() {
        return Lotto.from(numberGenerator.pickNumbersInRange(Lotto.LOTTO_NUMBER_COUNT));
    }
}
