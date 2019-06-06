package lotto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinStatistics;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        int price = InputView.inputMoney();
        Money money = new Money(price);

        OutputView.printNewLine();

        int amountOfCustoms = InputView.inputAmountOfCustom();
        LottoTickets lottoTickets = new LottoTickets(amountOfCustoms);

        OutputView.printNewLine();

        InputView.printCustomLottoNumbersMessage();
        while (lottoTickets.needMoreCustomLottoTicket()) {
            lottoTickets.putLottoTicket(InputView.inputLottoNumbers());
        }
        int amountOfAutos = money.getTicketCount() - amountOfCustoms;
        for (int i = 0; i < amountOfAutos; i++) {
            lottoTickets.putLottoTicket(null);
        }

        OutputView.printNewLine();

        OutputView.printLottoTickets(amountOfCustoms, money.getTicketCount(), lottoTickets.getLottoTickets());

        OutputView.printNewLine();

        InputView.printWinningLottoNumbersMessage();
        String winningLottoNumbers = InputView.inputLottoNumbers();
        int bonusBall = InputView.inputBonusBall();
        WinningLotto winningLotto = WinningLotto.of(winningLottoNumbers, bonusBall);

        OutputView.printNewLine();

        WinStatistics winStatistics = new WinStatistics(lottoTickets.getLottoTickets(), winningLotto);
        OutputView.printResult(winStatistics.getCountOfResult());
        OutputView.printProfitRate(winStatistics, money.getMoney());
    }
}
