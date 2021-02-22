package lotto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Seller;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class Application {

    public static void main(String[] args) {
        Seller seller = new Seller();

        Money money = new Money(LottoView.requestMoney());
        int count = money.count();
        List<Lotto> lottos = seller.sell(count);

        LottoView.buyLotto(count);
        LottoView.printLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
            new Lotto(seller.sell(LottoView.requestWinningNumber())),
            new LottoNumber(LottoView.requestBonusBallNumber())
        );

        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        for (int i = 0; i < Rank.values().length; i++) {
            ranks.put(Rank.values()[i], 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.findRank(lotto);
            int rankCount = ranks.get(rank);
            ranks.put(rank, rankCount+1);
        }
        LottoResult lottoResult = new LottoResult(ranks);
        LottoView.displayResultMessage();
        ranks.forEach((rank, rankCount) -> {
            LottoView.displayResult(rank, rankCount);
        });
        LottoView.displayEarningRate(lottoResult.findEarningRate(money.getMoney()));
    }
}