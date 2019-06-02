package lotto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        int price = InputView.inputMoney();
        Money money = new Money(price);

        int amountOfCustoms = InputView.inputAmountOfCustom();
        LottoTickets lottoTickets = new LottoTickets(amountOfCustoms);
        InputView.printCustomLottoNumbersMessage();
        while(lottoTickets.needMoreCustomLottoTicket()) {
            lottoTickets.putCustomLottoTicket(InputView.inputCustomLottoNumbers());
        }

    }
}
