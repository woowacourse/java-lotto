package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Seller;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class Application {

    public static void main(String[] args) {
        Seller seller = new Seller();

        int count = new Money(LottoView.requestMoney()).count();
        List<Lotto> lottos = seller.sell(count);

        LottoView.buyLotto(count);
        LottoView.printLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
            new Lotto(seller.sell(LottoView.requestWinningNumber())),
            new LottoNumber(LottoView.requestBonusBallNumber())
        );
    }
}