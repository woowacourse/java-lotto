package lotto.controller;

import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoController {
    public void run() {
        LottoTicketBundle lottoTicketBundle = getLottoTicketBundle();

        StatisticsDTO statisticsDTO = getStatisticsDTO(lottoTicketBundle);

        OutputView.printResult(statisticsDTO);
    }

    private LottoTicketBundle getLottoTicketBundle() {
        BettingMoneyRequestDTO bettingMoney = new BettingMoneyRequestDTO(InputView.inputBettingMoney());

        return LottoService.getLottoTicketBundle(bettingMoney);
    }

    private StatisticsDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle) {
        String winningNumber = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        return LottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);
    }

}
