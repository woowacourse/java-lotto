package lotto.controller;

import lotto.domain.LottoService;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.primitive.LottoNumber;
import lotto.domain.primitive.Money;
import lotto.domain.primitive.Ticket;
import lotto.domain.statistics.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public LottoController() {
    }

    public void start() {
        LottoService lottoService = new LottoService(new RandomLottoMachine());
        Ticket ticket = buyTicket();

        OutputView.printBuyTicket(ticket.getCount());
        lottoService.generateLottos(ticket);
        OutputView.printLottoResults(lottoService.getLottos());

        lottoService.scratchLotto(getWinningLotto());

        OutputView.printWinningStats(lottoService.getRatingCounter(), lottoService.getEarningRate(ticket.getPrice()));
    }

    private Ticket buyTicket() {
        try {
            return tryBuyTicket();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyTicket();
        }
    }

    private Ticket tryBuyTicket() {
        OutputView.getMessage("구입금액을 입력해 주세요.");
        int money = InputView.getInt();
        return new Ticket(new Money(money));
    }

    private WinningLotto getWinningLotto() {
        try {
            return tryGetWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private WinningLotto tryGetWinningLotto() {
        Lotto lotto = Lotto.createByInteger(InputView.getWinningNumbers());
        OutputView.getMessage("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(InputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }
}
