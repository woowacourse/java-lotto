package lotto;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.view.InputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        Lottos lottos = LottoMachine.buyLottos(InputView.inputMoney());
    }
}
