package lotto.controller;

import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.LottoTicketResponseDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.Scanner;

public class LottoController {


    private static final InputView inputView = new InputView(new Scanner(System.in));
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        LottoTicketBundle lottoTicketBundle = getLottoTicketBundle();
        OutputView.printBuyTickets(LottoTicketResponseDTO.ofList(lottoTicketBundle));

        StatisticsResponseDTO statisticsResponseDTO = getStatisticsDTO(lottoTicketBundle);
        OutputView.printResult(statisticsResponseDTO);
    }

    private LottoTicketBundle getLottoTicketBundle() {
        BettingMoneyRequestDTO bettingMoney = new BettingMoneyRequestDTO(inputView.inputBettingMoney());

        return lottoService.getLottoTicketBundle(bettingMoney);
    }

    private StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle) {
        String winningNumber = inputView.inputWinningNumber();
        int bonusNumber = inputView.inputBonusNumber();
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        return lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);
    }

}
