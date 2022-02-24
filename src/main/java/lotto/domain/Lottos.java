package lotto.domain;

import static lotto.utils.LottoGenerator.generateLottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private static final Money LOTTO_PRICE = new Money(1000);

    private final List<Lotto> lottos = new ArrayList<>();

    public void purchase(Money money) {
        int purchaseNumber = money.canBuyNumber(LOTTO_PRICE);
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
    }

    public Map<LottoPrize, Integer> confirmWinnings(WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> result = new EnumMap<>(LottoPrize.class);
        for (LottoPrize prize : LottoPrize.values()) {
            result.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            LottoPrize prize = lotto.confirmWinning(winningNumbers);
            result.put(prize, result.get(prize) + 1);
        }

        return result;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
