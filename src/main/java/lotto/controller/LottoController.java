package lotto.controller;

import java.util.List;
import lotto.domain.AutoLottoMachine;
import lotto.domain.LottoMachine;
import lotto.domain.LottoTicket;
import lotto.view.InputView;

public class LottoController {
    private final InputView inputView;
    private LottoMachine lottoMachine;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        lottoMachine = new AutoLottoMachine();
        int lottoMoney = inputView.inputDecimal();

        int numberOfTickets = lottoMoney / 1000;
        List<LottoTicket> lottoTickets = lottoMachine.createTickets(numberOfTickets);
        System.out.printf("%d장 발권되었습니다.", lottoTickets.size());
    }
}
