package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        LottoService lottoService = new LottoService();

        Ticket ticket = buyTicketWithManualCount();
        generateLottos(lottoService, ticket);

        outputView.printBuyTicket(ticket.getManualCount(), ticket.getRandomCount());
        outputView.printLottoResults(lottoService.getLottos());

        lottoService.scratchLotto(getWinningLotto());

        outputView.printWinningStats(lottoService.getRatingCounter(), lottoService.getEarningRate(ticket.getPrice()));
    }
    
    private Ticket buyTicketWithManualCount() {
        Ticket ticket = buyTicket();
        setManualCount(ticket);
        return ticket;
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

    private void setManualCount(Ticket ticket) {
        try {
            trySetManualCount(ticket);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            setManualCount(ticket);
        }
    }

    private void trySetManualCount(Ticket ticket) {
        outputView.printMessage("수동으로 구매할 로또 수를 입력해 주세요");
        ticket.setManualCount(inputView.getInt());
    }

    private void generateLottos(LottoService lottoService, Ticket ticket) {
        outputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < ticket.getManualCount(); i++) {
            lottoService.addLotto(getManualLottoNumbers());
        }
        lottoService.generateLottos(ticket.getRandomCount());
    }

    private Lotto getManualLottoNumbers() {
        try {
            return Lotto.of(inputView.getLottoNumbers());
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return getManualLottoNumbers();
        }
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
        Lotto lotto = Lotto.of(inputView.getLottoNumbers());
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = LottoNumber.valueOf(inputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }
}
