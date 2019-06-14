package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUIApplication {
    public static void main(String[] args) {
        Money money = InputView.getMoney();
        int numberOfManualLotto = InputView.getNumberOfManualLotto(money);

        LottoTickets lottoTickets = getLottoTickets(money, numberOfManualLotto);

        OutputView.printLottoNumbers(money, numberOfManualLotto);
        OutputView.printLottoTickets(lottoTickets);

        WinningLotto winningLotto = InputView.getWinningLotto();
        LottoResult lottoResult = lottoTickets.getLottoResult(winningLotto);

        OutputView.printLottoStatistics(lottoResult);
    }

    private static LottoTickets getLottoTickets(Money money, int numberOfManualLotto) {
        if (numberOfManualLotto > 0) {
            List<Lotto> manualLotto = InputView.getManualLotto(numberOfManualLotto);
            return LottoMachine.generateTickets(money, manualLotto);
        }
        return LottoMachine.generateTickets(money);
    }
}
