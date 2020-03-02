import domain.buyinginformation.BuyingInformation;
import domain.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.WinningNumbers;
import domain.lottonumbers.lottonumber.LottoNumber;
import domain.lottostore.LottoStore;
import domain.lottostore.ManualBuyingStrategy;
import domain.lottostore.RandomBuyingStrategy;
import domain.result.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class LottoApplication {

    public static void main(String[] args) {
        BuyingInformation buyingInformation = enterBuyingInformation();
        List<LottoTicket> tickets = LottoStore.generateTickets(new ManualBuyingStrategy(), buyingInformation);

        printTickets(tickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        LottoResult lottoResult = LottoResult.confirmResult(tickets, winningNumbers);

        printResult(lottoResult, buyingInformation.getBuyingMoney());
    }

    private static BuyingInformation enterBuyingInformation() {
        return new BuyingInformation(enterMoney(), enterManualLottoTickets());
    }

    private static Money enterMoney() {
        return new Money(InputView.enterMoney());
    }

    private static List<LottoTicket> enterManualLottoTickets() {
        return InputView.enterManualNumbers().stream()
                .map(LottoApplication::parseNumbersToTicket)
                .collect(toList());
    }

    private static LottoTicket parseNumbersToTicket(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }

    private static void printTickets(List<LottoTicket> tickets) {
        OutputView.printNumberOfTickets(tickets.size());
        OutputView.printTickets(tickets);
    }

    private static WinningNumbers enterWinningNumbers() {
        return new WinningNumbers(enterTicket(), enterBonusNumber());
    }

    private static LottoTicket enterTicket() {
        return parseNumbersToTicket(InputView.enterLastWeekWinningNumbers());
    }

    private static LottoNumber enterBonusNumber() {
        return LottoNumber.of(InputView.enterBonusNumber());
    }

    private static void printResult(LottoResult lottoResult, Money money) {
        OutputView.printLottoResults(lottoResult);
        OutputView.printProfit(lottoResult.calculateProfit(money));
    }
}