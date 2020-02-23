package lotto.controller;

import lotto.domain.ticket.AutoLottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.LottoTicketDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

public class LottoController {
    private LottoService service = new LottoService(new AutoLottoMachine());

    public void run() {
        BettingMoneyDTO bettingMoney = new BettingMoneyDTO(InputView.inputBettingMoney());

        LottoTicketBundle lottoTicketBundle = service.createLottoTicketBundle(bettingMoney);

        OutputView.printLottoTickets(LottoTicketDTO.createLottoTicketDTOS(lottoTicketBundle));

        ResultDTO resultDTO = service.getResult(createWinLottoTicketDTO(), lottoTicketBundle);

        OutputView.printResult(resultDTO);
    }

    private WinLottoTicketDTO createWinLottoTicketDTO() {
        String winningNumber = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();
        return new WinLottoTicketDTO(winningNumber, bonusNumber);
    }
}
