package model;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public List<Lotto> issueLottos(final PurchaseAmount purchaseAmount) {
        final int lottoCount = purchaseAmount.calculateLottoCount();
        final List<Lotto> lottos = new ArrayList<>();

        while (lottos.size() == lottoCount) {
            lottos.add(Lotto.from(generateUniqueRandomNumbers()));
        }

        return lottos;
    }

    private List<Integer> generateUniqueRandomNumbers() {
        final List<Integer> randoms = new ArrayList<>();

        while (randoms.size() < 6) {
            int randomNumber = generateRandomNumber();
            if (!randoms.contains(randomNumber)) {
                randoms.add(randomNumber);
            }
        }
        return randoms;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 45 + 1);

    }
}
