package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Seller;
import lotto.view.LottoView;

public class Application {

    public static void main(String[] args) {
        Seller seller = new Seller();

        int count = new Money(LottoView.requestMoney()).count();
        List<Lotto> lottos = seller.sell(count);

        LottoView.buyLotto(count);
        LottoView.printLottos(lottos);
    }
}