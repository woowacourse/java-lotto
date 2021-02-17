package lotto.controller;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.AutoLottoMachine;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Prize;
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
        int lottoMoney = inputView.takeLottoMoney();

        int numberOfTickets = lottoMoney / 1000;
        List<LottoTicket> lottoTickets = lottoMachine.createTickets(numberOfTickets);
        outputView.printTicketsSize(lottoTickets.size());

        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.takeBonusNumber();
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningNumbers, bonusNumber);

        LottoResult lottoResult = new LottoResult(lottoTickets, winningLottoTicket);
    }
}
