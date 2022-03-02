package domain.player;

import domain.Lotto.Lotto;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.Rank;
import domain.Result;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Money money;
    private List<Lotto> lottos;

    public Player(Money money) {
        this.money = money;
        this.lottos = new ArrayList<>();
    }

    private int getNumberOfPurchases() {
        return money.determineQuantity();
    }

    public void purchaseManualLotto(LottoGenerator lottoGenerator, List<List<Integer>> inputLottoNumbers) {
        for (List<Integer> lottoNumbers : inputLottoNumbers) {
            purchase(lottoGenerator, lottoNumbers);
            money.subAmount();
        }
    }

    public void purchaseAutoLotto(LottoGenerator lottoGenerator, List<Integer> lottoNumbers) {
        int numberOfPurchases = getNumberOfPurchases();
        for (int i = 0; i < numberOfPurchases; i++) {
            purchase(lottoGenerator, lottoNumbers);
        }
    }

    private void purchase(LottoGenerator lottoGenerator, List<Integer> lottoNumbers) {
        lottos.add(lottoGenerator.generateLotto(lottoNumbers));
    }

    public List<Result> judgeAll(WinningLotto winningLotto) {
        List<Result> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            result.add(winningLotto.judge(lotto));
        }
        return result;
    }

    public double calculateIncomeRate(List<Result> results) {
        double totalIncome = Rank.calculateAllResult(results);
        return totalIncome / money.getAmount();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void checkPossible(int manualQuantity) {
        money.isEnoughMoney(manualQuantity);
    }
}
