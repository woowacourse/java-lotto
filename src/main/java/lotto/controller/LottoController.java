package lotto.controller;

import lotto.domain.ticket.AutoLottoMachine;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.LottoTicketDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.ArrayList;
import java.util.List;

public class LottoController {


    private LottoService service = new LottoService(new AutoLottoMachine());

    public void run() {
        BettingMoneyDTO bettingMoney = new BettingMoneyDTO(InputView.inputBettingMoney());

        LottoTicketBundle lottoTicketBundle = service.createLottoTicketBundle(bettingMoney);

        OutputView.printLottoTickets(convertToLottoTicketDTOS(lottoTicketBundle));

        List<ResultDTO> resultDTOS = service.computeOverallWinResult(createWinLottoTicketDTO(), lottoTicketBundle);

        double rate = service.computeAnalysis(resultDTOS);

        OutputView.printResult(resultDTOS, rate);
    }

    private List<LottoTicketDTO> convertToLottoTicketDTOS(LottoTicketBundle lottoTicketBundle) {
        List<LottoTicketDTO> lottoTicketDTOS = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            lottoTicketDTOS.add(new LottoTicketDTO(lottoTicket));
        }

        return lottoTicketDTOS;
    }

    private WinLottoTicketDTO createWinLottoTicketDTO() {
        String winNumbers = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();

        return new WinLottoTicketDTO(winNumbers, bonusNumber);
    }
}
