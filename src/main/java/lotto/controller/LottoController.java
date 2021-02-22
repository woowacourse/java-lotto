package lotto.controller;

import lotto.domain.*;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        LottoService lottoService = new LottoService(new RandomLottoMachine());
        Ticket ticket = buyTicket();

        outputView.printBuyTicket(ticket.getCount());
        lottoService.generateLottos(ticket);
        outputView.printLottoResults(lottoService.getLottos());

        lottoService.scratchLotto(getWinningLotto());

        outputView.printWinningStats(lottoService.getRatingCounter(), lottoService.getEarningRate(ticket.getPrice()));
    }

    private Ticket buyTicket() {
        try {
            return tryBuyTicket();
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return buyTicket();
        }
    }

    private Ticket tryBuyTicket() {
        outputView.printMessage("구입금액을 입력해 주세요.");
        int money = inputView.getInt();
        return new Ticket(new Money(money));
    }

    private WinningLotto getWinningLotto() {
        try {
            return tryGetWinningLotto();
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private WinningLotto tryGetWinningLotto() {
        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        Lotto lotto = Lotto.createByInteger(inputView.getWinningNumbers());
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(inputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }
}
