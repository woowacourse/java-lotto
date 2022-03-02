package domain.player;

import domain.Lotto.Lotto;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

    private final Money money;
    private final List<Lotto> lottos;

    public Player(int money) {
        this.money = new Money(money);
        this.lottos = new ArrayList<>();
    }

    public boolean canBuyLotto() {
        return money.isBiggerThanLottoPrice();
    }

    public void purchaseLotto(Lotto lotto) {
        money.deductMoney();
        lottos.add(lotto);
    }

    public List<Result> judgeAll(WinningLotto winningLotto) {
        List<Result> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            result.add(lotto.judge(winningLotto));
        }
        return result;
    }

    public double calculateIncomeRate(double totalIncome) {
        return totalIncome / (lottos.size() * MINIMUM_PURCHASE_AMOUNT);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
