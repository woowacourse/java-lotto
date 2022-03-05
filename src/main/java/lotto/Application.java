package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoGame;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame(InputView.requestMoney(), InputView.requestManualLotto());

        LottoController lottoController = new LottoController(lottoGame);
        LottoTicketsDto lottoTicketsDto = lottoController.publishLottoTickets();
        OutputView.displayLottoTickets(lottoTicketsDto);

        LottoResultDto lottoResultDto = lottoController.matchLottoTickets(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber());
        OutputView.displayLottoResult(lottoResultDto);
    }
}
