package lotto;

import lotto.domain.LottoResults;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.RewardTicketFactory;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        Money money = new Money(ConsoleInputView.inputMoney());
        ConsoleOutputView.printAmount(money);

        LottoTickets lottoTickets = LottoTicketsFactory.create(money);
        ConsoleOutputView.printTickets(lottoTickets);

        LottoTicket rewardTicket = RewardTicketFactory.create(ConsoleInputView.inputRewardTicket());

        LottoResults lottoResults = new LottoResults(lottoTickets, rewardTicket, money);
        ConsoleOutputView.printResults(lottoResults);
    }
}
