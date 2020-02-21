package lotto.controller;

import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.LottoTicketResponseDTO;
import lotto.view.dto.PrizeResponseBundleDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.Scanner;

public class LottoController {

    private static final InputView inputView = new InputView(new Scanner(System.in));
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        BettingMoney bettingMoney = new BettingMoney(inputView.inputBettingMoney());
        OutputView.printBuyTicketCount(bettingMoney.getTicketCount());

        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoney);
        OutputView.printBuyTickets(LottoTicketResponseDTO.ofList(lottoTicketBundle));

        PrizeResponseBundleDTO prizeResponseBundleDTO = getPrizeResponseBundleDTO(lottoTicketBundle);
        OutputView.printResult(prizeResponseBundleDTO);
    }

    private PrizeResponseBundleDTO getPrizeResponseBundleDTO(LottoTicketBundle lottoTicketBundle) {
        String winningNumber = inputView.inputWinningNumber();
        int bonusNumber = inputView.inputBonusNumber();
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        return lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);
    }

}
