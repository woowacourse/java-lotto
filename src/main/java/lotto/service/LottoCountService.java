package lotto.service;

import lotto.domain.LottoBuyingMoney;
import lotto.domain.LottoCount;
import org.javatuples.Pair;

import static lotto.service.ServiceUtils.parseInt;

public class LottoCountService {
    public static Pair<Integer, Integer> calculateNumOfLottos(int lottoBuyingMoney, String numOfCustomLottos) {
        LottoBuyingMoney money = new LottoBuyingMoney(lottoBuyingMoney);
        LottoCount count = new LottoCount(money, parseInt(numOfCustomLottos));
        return Pair.with(count.custom(), count.random());
    }
}
