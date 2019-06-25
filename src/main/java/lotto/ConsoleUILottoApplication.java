package lotto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinStatistics;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.exception.UnexpectedInputRangeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Money money = getMoney();
        int amountOfCustoms = getAmountOfCustoms(money.getTicketCount());

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

        try {
            return WinningLotto.of(winningLottoNumbers, bonusBall);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }

    private static LottoTickets getLottoTickets(Money money, int amountOfCustoms) {
        List<String> inputOfCustomLottoNumbers = InputView.inputCustomLottoNumbers(amountOfCustoms);

        return LottoTicketsFactory.getInstance().create(money, inputOfCustomLottoNumbers);
    }

    private static int getAmountOfCustoms(int maxCount) {
        return InputView.inputAmountOfCustom(maxCount);
    }

    private static Money getMoney() {
        try {
            int price = InputView.inputMoney();
            return new Money(price);
        } catch (UnexpectedInputRangeException e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

}
