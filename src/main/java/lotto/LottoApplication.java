package lotto;

import lotto.cotroller.LottosController;
import lotto.cotroller.MoneyController;
import lotto.cotroller.RankController;
import lotto.domain.Money;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.rank.Rank;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        Money userMoney = MoneyController.request();

        Lottos lottos = LottosController.request(userMoney);
        OutputView.printLottos(lottos);

        Rank rank = RankController.request(lottos);
        OutputView.printRankState(rank);
        OutputView.printRateOfReturn(rank.rateOfReturn(userMoney.getMoney()));
    }
}
