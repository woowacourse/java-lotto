import domain.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.WinningNumbers;
import domain.lottonumbers.lottonumber.LottoNumber;
import domain.lottostore.RandomLottoStore;
import domain.result.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
        List<LottoTicket> randomTickets = new RandomLottoStore().generateTickets(money);

        printTickets(randomTickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        LottoResult lottoResult = LottoResult.confirmResult(randomTickets, winningNumbers);

        printResult(lottoResult, money);
    }

    private static Money enterMoney() {
        return new Money(InputView.enterMoney());
    }

    private static void printTickets(List<LottoTicket> tickets) {
        OutputView.printNumberOfTickets(tickets.size());
        OutputView.printTickets(tickets);
    }

    private static WinningNumbers enterWinningNumbers() {
        return new WinningNumbers(enterTicket(), enterBonusNumber());
    }

    private static LottoTicket enterTicket() {
        return InputView.enterLastWeekWinningNumbers().stream()
                .map(LottoNumber::new)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }

    private static LottoNumber enterBonusNumber() {
        return LottoNumber.of(InputView.enterBonusNumber());
    }

    private static void printResult(LottoResult lottoResult, Money money) {
        OutputView.printLottoResults(lottoResult);
        OutputView.printProfit(lottoResult.calculateProfit(money));
    }
}