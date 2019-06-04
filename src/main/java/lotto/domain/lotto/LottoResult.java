package lotto.domain.lotto;

import lotto.domain.money.Money;
import lotto.domain.money.Prize;
import lotto.domain.money.PrizeInfo;

import java.util.List;

public class LottoResult {
    private Money money;
    private PrizeInfo prizeInfo;


    private LottoResult(Money money, List<Prize> prizes) {
        prizeInfo = new PrizeInfo(prizes);
        this.money = money;
    }

    public static LottoResult create(Money money, List<Prize> prizes) {
        return new LottoResult(money, prizes);
    }

    public double getProfitRate() {
        double sum = 0;
        for (Prize prize : Prize.values()) {
            sum += prize.getPrizeMoney() * prizeInfo.get(prize);
        }
        return money.calculatePercentage(sum);
    }

    public int getCount(Prize prize) {
        return prizeInfo.get(prize);
    }
}
