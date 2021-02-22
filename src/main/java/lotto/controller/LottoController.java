package lotto.controller;

import lotto.domain.AutoLottoMachine;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    private LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        lottoMachine = new AutoLottoMachine();

        int lottoPurchaseMoney = inputView.takeLottoMoney();
        LottoTickets lottoTickets = buyLottoTicket(lottoPurchaseMoney);
        WinningLottoTicket winningLottoTicket = createWinningLottoTicket();
        LottoResult lottoResult = lottoTickets.calculateLottoResult(winningLottoTicket);

        outputView.printLottoResult(lottoResult, lottoPurchaseMoney);
    }

    private LottoTickets buyLottoTicket(int lottoPurchaseMoney) {
        LottoTickets lottoTickets = lottoMachine.createTicketsByMoney(lottoPurchaseMoney);
        outputView.printAllLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private WinningLottoTicket createWinningLottoTicket() {
        LottoTicket winningTicket = new LottoTicket(inputView.inputWinningNumbers());
        int bonusNumber = inputView.takeBonusNumber();
        return new WinningLottoTicket(winningTicket, bonusNumber);
    }
}
