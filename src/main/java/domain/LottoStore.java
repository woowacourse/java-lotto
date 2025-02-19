package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private final NumbersGenerator numbersGenerator;
    private final PurchaseAmount purchaseAmount;

    public LottoStore(final NumbersGenerator numbersGenerator, final PurchaseAmount purchaseAmount) {
        this.numbersGenerator = numbersGenerator;
        this.purchaseAmount = purchaseAmount;
    }

    public List<Lotto> issueLottos() {
        final int count = purchaseAmount.getAvailableLottoCount();
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(numbersGenerator.generate()));
        }
        return lottos;
    }

}
