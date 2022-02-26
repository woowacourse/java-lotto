package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        OutputView.displayLottoTickets(lottoController.buyLottoTickets(InputView.requestMoney()));
        OutputView.displayLottoResult(lottoController.matchLottoTickets(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber()));
    }
}
