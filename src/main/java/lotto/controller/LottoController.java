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
        Ticket ticket = buyTicketWithManualCount();
        LottoService lottoService = new LottoService(ticket);
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
        int money = inputView.getTicketMoney();
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
        ticket.setManualCount(inputView.getManualLottoCount());
    }

    private void generateLottos(LottoService lottoService, Ticket ticket) {
        outputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < ticket.getManualCount(); i++) {
            lottoService.addLotto(getManualLottoNumbers());
        }
        lottoService.generateLottos();
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
        Lotto lotto = Lotto.of(inputView.getWinningLottoNumbers());
        LottoNumber bonusNumber = LottoNumber.valueOf(inputView.getBonusBallNumber());
        return new WinningLotto(lotto, bonusNumber);
    }
}
