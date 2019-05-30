package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private Money money;
    private PrizeInfo prizeInfo;


    public LottoResult(Money money, List<Prize> prizes) {
        prizeInfo = new PrizeInfo(prizes);
        this.money = money;
    }

    public double getPercentage() {
        int sum = 0;
        for (Prize prize : Prize.values()) {
            sum += prize.getPrizeMoney() * prizeInfo.get(prize);
        }
        return money.calculatePercentage(sum);
    }

    public int getCount(Prize prize) {
        return prizeInfo.get(prize);
    }
}
