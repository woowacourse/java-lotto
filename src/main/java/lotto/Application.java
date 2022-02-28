package lotto;

import lotto.controller.LottoController;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();

        LottoTicketsDto lottoTicketsDto = lottoController.buyLottoTickets(InputView.requestMoney());
        OutputView.displayLottoTickets(lottoTicketsDto);

        LottoResultDto lottoResultDto = lottoController.matchLottoTickets(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber());
        OutputView.displayLottoResult(lottoResultDto);
    }
}
