package lotto.controller;

import lotto.domain.Count;
import lotto.domain.Money;
import lotto.exception.UnderLottoUnitMoney;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void play() {
        Money money = generateMoney();
        Count TicketCount = new Count(money.generateLottoTicketCount());
        Count manualTicketCount = generateManualTicketCount(TicketCount);

    }

    private Money generateMoney() {
        String inputMoney = InputView.inputMoney();

        try {
            return new Money(inputMoney);
        }catch (UnderLottoUnitMoney e){
            OutputView.printErrorMessage(e.getMessage());
            OutputView.printChangeMoney(inputMoney);
        }
        catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return generateMoney();
    }

    private Count generateManualTicketCount(Count allTicketCount){
        Count manualTicketCount = new Count(InputView.inputManualLottoCount());

        try {
            manualTicketCount.validateOverTicketCount(allTicketCount);
            return manualTicketCount;
        }catch (RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
            generateManualTicketCount(allTicketCount);
        }
        return manualTicketCount;
    }

}
