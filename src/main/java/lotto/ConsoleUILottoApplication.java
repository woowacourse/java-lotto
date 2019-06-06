package lotto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinStatistics;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Money money = getMoney();
        int amountOfCustoms = getAmountOfCustoms();
        LottoTickets lottoTickets = getLottoTickets(money, amountOfCustoms);
        printAllLottoTickets(money, amountOfCustoms, lottoTickets);
        WinningLotto winningLotto = getWinningLotto();
        printLottoResult(money, lottoTickets, winningLotto);
    }

    private static void printAllLottoTickets(Money money, int amountOfCustoms, LottoTickets lottoTickets) {
        OutputView.printLottoTickets(amountOfCustoms, money.getTicketCount(), lottoTickets.getLottoTickets());
    }

    private static void printLottoResult(Money money, LottoTickets lottoTickets, WinningLotto winningLotto) {
        WinStatistics winStatistics = new WinStatistics(lottoTickets.getLottoTickets(), winningLotto);
        OutputView.printResult(winStatistics.getCountOfResult());
        OutputView.printProfitRate(winStatistics, money.getMoney());
    }

    private static WinningLotto getWinningLotto() {
        InputView.printWinningLottoNumbersMessage();
        String winningLottoNumbers = InputView.inputLottoNumbers();
        int bonusBall = InputView.inputBonusBall();
        return WinningLotto.of(winningLottoNumbers, bonusBall);
    }

    private static LottoTickets getLottoTickets(Money money, int amountOfCustoms) {
        LottoTickets lottoTickets = new LottoTickets(amountOfCustoms);

        InputView.printCustomLottoNumbersMessage();
        purchaseCustomLottoTickets(lottoTickets);
        purchaseAutoLottoTickets(money, amountOfCustoms, lottoTickets);

        return lottoTickets;
    }

    private static void purchaseAutoLottoTickets(Money money, int amountOfCustoms, LottoTickets lottoTickets) {
        int amountOfAutos = money.getTicketCount() - amountOfCustoms;
        for (int i = 0; i < amountOfAutos; i++) {
            lottoTickets.putLottoTicket(null);
        }
    }

    private static void purchaseCustomLottoTickets(LottoTickets lottoTickets) {
        while (lottoTickets.needMoreCustomLottoTicket()) {
            lottoTickets.putLottoTicket(InputView.inputLottoNumbers());
        }
    }

    private static int getAmountOfCustoms() {
        return InputView.inputAmountOfCustom();
    }

    private static Money getMoney() {
        int price = InputView.inputMoney();
        return new Money(price);
    }
}
