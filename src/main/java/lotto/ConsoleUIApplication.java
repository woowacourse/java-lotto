package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUIApplication {
    public static void main(String[] args) {
        Money money = InputView.getMoney();
        int numberOfManualLotto = InputView.getNumberOfManualLotto(money);

        LottoTickets lottoTickets = LottoMachine.getLottoTickets(money, numberOfManualLotto);

        OutputView.printLottoNumbers(money, numberOfManualLotto);
        OutputView.printLottoTickets(lottoTickets);

        WinningLotto winningLotto = InputView.getWinningLotto();
        LottoResult lottoResult = lottoTickets.getLottoResult(winningLotto);

        OutputView.printLottoStatistics(lottoResult);
    }
}
