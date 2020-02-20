package lotto.controller;

import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.RealLottoCompany;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.LottoTicketBundleResponseDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoController {
    private LottoService service = new LottoService(new RealLottoCompany());

    public void run() {
        LottoTicketBundle lottoTicketBundle = getLottoTicketBundle();

        OutputView.printLottoTicket(new LottoTicketBundleResponseDTO(lottoTicketBundle));

        StatisticsResponseDTO statisticsResponseDTO = getStatisticsDTO(lottoTicketBundle);

        OutputView.printResult(statisticsResponseDTO);
    }

    private LottoTicketBundle getLottoTicketBundle() {
        BettingMoneyRequestDTO bettingMoney = new BettingMoneyRequestDTO(InputView.inputBettingMoney());

        return service.getLottoTicketBundle(bettingMoney);
    }

    private StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle) {
        String winningNumber = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        return service.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);
    }

}
