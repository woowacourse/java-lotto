package lotto;

import lotto.domain.*;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.RewardTicketFactory;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(ConsoleInputView.inputMoney());

        ConsoleOutputView.printAmount(lottoMoney);

        LottoTickets lottoTickets = LottoTicketsFactory.create(lottoMoney);
        ConsoleOutputView.printTickets(lottoTickets);

        LottoTicket rewardTicket = RewardTicketFactory.create(ConsoleInputView.inputRewardTicket());

        LottoResults lottoResults = new LottoResults(lottoTickets, rewardTicket, lottoMoney);
        ConsoleOutputView.printResults(lottoResults);
    }
}
