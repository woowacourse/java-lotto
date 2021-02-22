package lotto.controller;

import lotto.domain.*;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

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

        getManualCount(ticket);
        getManualLottoNumbers(ticket,lottoService);

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

    private void getManualCount(Ticket ticket) {
        try {
            tryGetManualCount(ticket);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            getManualCount(ticket);
        }
    }

    private void tryGetManualCount(Ticket ticket) {
        outputView.printMessage("수동으로 구매할 로또 수를 입력해 주세요");
        ticket.setManualCount(inputView.getInt());
    }

    private void getManualLottoNumbers(Ticket ticket, LottoService lottoService) {
        try {
            outputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
            for (int i = 0; i < ticket.getManualCount(); i++) {
                getManualLottoNumber(inputView.getWinningNumbers(), lottoService);
            }
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            getManualCount(ticket);
        }
    }

    private void getManualLottoNumber(List<Integer> lottoNumbers, LottoService lottoService) {
        lottoService.generateManualLotto(Lotto.createByInteger(lottoNumbers));
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
