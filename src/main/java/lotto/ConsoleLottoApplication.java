package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        Money myMoney = new Money(money);
        int amount = myMoney.ticketAmount();

        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            lottoTickets.add(LottoTicketFactory.create());
        }

        List<Integer> rewardNumbers = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getSameCount(new LottoTicket(rewardNumbers)));
        }
    }
}
