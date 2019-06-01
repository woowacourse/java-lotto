package lotto;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        int inputMoney = InputView.receiveLottoMoney();
        LottoMoney lottoMoney = new LottoMoney(inputMoney);

        List<String> manualLottos = InputView.receiveManualLotto();

        Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());
        OutputView.printLottos(totalLottos, manualLottos.size());
    }
}
