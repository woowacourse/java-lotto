package lotto.controller;

import lotto.domain.*;
import lotto.exception.UnderLottoUnitMoney;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void play() {
        Money money = generateMoney();
        Count autoTicketCount = new Count(money.generateLottoTicketCount());
        Count manualTicketCount = generateManualTicketCount(autoTicketCount);

        OutputView.printInputManualLottoTicket();
        for (int i = 0; i < manualTicketCount.getTicketCount(); i++) {
            LottoTickets.insertLottoTicket(generateManualLottoTicket());
        }
        generateAutoTicket(autoTicketCount, manualTicketCount);
        OutputView.printLottoTicket();
        OutputView.printChangeMoney(money.changeMoney());

        OutputView.printWinningTicket();
    }

    private LottoTicket generateManualLottoTicket() {
        try {
            return new LottoTicket(InputView.inputManualLottoTicket());
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateManualLottoTicket();
        }
    }

    private void generateAutoTicket(Count autoTicketCount, Count manualTicketCount) {
        autoTicketCount.calculateAutoTicketCount(manualTicketCount);
        OutputView.printLottoTicketCount(manualTicketCount, autoTicketCount);
        for (int i = 0; i < autoTicketCount.getTicketCount(); i++) {
            LottoBalls.shuffle();
            LottoTickets.insertLottoTicket(new LottoTicket(LottoBalls.generateLottoTicket()));
        }
    }

    private Money generateMoney() {
        String inputMoney = InputView.inputMoney();

        try {
            return new Money(inputMoney);
        } catch (UnderLottoUnitMoney e) {
            OutputView.printErrorMessage(e.getMessage());
            OutputView.printChangeMoney(inputMoney);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return generateMoney();
    }

    private Count generateManualTicketCount(Count allTicketCount) {
        try {
            Count manualTicketCount = new Count(InputView.inputManualLottoCount());

            manualTicketCount.validateOverTicketCount(allTicketCount);
            return manualTicketCount;
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateManualTicketCount(allTicketCount);
        }
    }
}
