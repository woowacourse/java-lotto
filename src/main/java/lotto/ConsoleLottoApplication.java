package lotto;

import lotto.domain.UserLottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.Money;
import lotto.view.ConsoleInputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        int money = ConsoleInputView.inputMoney();
        Money myMoney = new Money(money);
        int amount = myMoney.ticketAmount();

        List<UserLottoTicket> userLottoTickets = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            userLottoTickets.add(LottoTicketFactory.create());
        }

        List<Integer> rewardNumbers = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );

        for (UserLottoTicket userLottoTicket : userLottoTickets) {
            System.out.println(userLottoTicket.getSameCount(new UserLottoTicket(rewardNumbers)));
        }
    }
}
