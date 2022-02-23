package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchasedLotto {

    private List<Lotto> lottos = new ArrayList<>();
    private int totalPrice = 0;
    private Map<WinnerPrice, Integer> finalResult = new HashMap<>();

    public PurchasedLotto() {
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public Map<WinnerPrice, Integer> calculateWinning(Lotto winningLotto, LottoNumber bonus) {
        initFinalResult();
        lottos.stream()
                .forEach(lotto -> {
                    WinnerPrice winnerPrice = lotto.calculateRank(winningLotto, bonus);
                    finalResult.put(winnerPrice, 1);
                });
        return finalResult;
    }

    private void initFinalResult() {
        WinnerPrice.getWinnerPrices().stream()
                .forEach(winnerPrice -> finalResult.put(winnerPrice, 0));
    }
}
