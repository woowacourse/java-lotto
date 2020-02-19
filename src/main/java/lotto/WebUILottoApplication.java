package lotto;

import lotto.domain.WinLotto;
import lotto.view.InputView;

public class WebUILottoApplication {
    public static void main(String[] args) {
        // Money money = new Money(InputView.inputBuyMoney());
        // int num = money.divideThousand();
        // OutputView.printLottoCount(num);
        // Lotteris lotteris = new Lotteris(num);
        // OutputView.printLotteris(lotteris);
        WinLotto winLotto = new WinLotto(InputView.inputWinNumber());
    }

}
