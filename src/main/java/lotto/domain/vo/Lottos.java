package lotto.domain.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.domain.LottoPrize;
import lotto.domain.generator.LottoGenerator;

public class Lottos {

    private static final Money LOTTO_PRICE = new Money(1000);

    private final List<Lotto> lottos = new ArrayList<>();

    public void purchase(Money money) {
        int purchaseNumber = money.canBuyNumber(LOTTO_PRICE);
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(LottoGenerator.generate());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Map<LottoPrize, Integer> confirmWinnings(WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> result = new HashMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            result.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            LottoPrize prize = lotto.confirmWinning(winningNumbers);
            result.put(prize, result.get(prize) + 1);
        }

        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
