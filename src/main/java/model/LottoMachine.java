package model;

import generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {


    private final NumberGenerator numberGenerator;

    public LottoMachine(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final PurchaseAmount purchaseAmount) {
        final int lottoCount = purchaseAmount.calculateLottoCount();
        final List<Lotto> lottos = new ArrayList<>();

        while (lottos.size() < lottoCount) {
            lottos.add(Lotto.from(numberGenerator.pickNumbersInRange(Lotto.LOTTO_NUMBER_COUNT)));
        }

        return lottos;
    }
}
