package creator;

import domain.Lotto;
import domain.Profit;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
import java.util.Map;
import utils.InputParser;

public class LottoCreator {

    private LottoCreator() {
    }

    public static Ticket createTicket(int price) {
        return Ticket.create(price);
    }

    public static Lotto createLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return Lotto.from(parsedNumbers);
    }

    public static WinningNumber createWinningNumber(Lotto winningNumbers, int bonusNumber) {
        return WinningNumber.of(winningNumbers, bonusNumber);
    }

    public static Profit createProfit(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        return Profit.of(calculateResult, purchaseAmount);
    }
}
