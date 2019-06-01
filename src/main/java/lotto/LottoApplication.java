package lotto;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.userPrice());
        int manualLottoNumber = money.checkPurchaseLotto(InputView.purchaseManualLotto());
        List<Lotto> manualLottos = InputView.manualLottoNumber(manualLottoNumber);
        Lottos lottos = new Lottos(manualLottos, money.getAutoLottoNumber(manualLottoNumber));

        OutputView.printLottos(lottos);
    }
}
