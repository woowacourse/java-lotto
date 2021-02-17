package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LottoManager {
    private Supplier<List<Integer>> generator;

    public LottoManager(final Supplier<List<Integer>> generator) {
        this.generator = generator;
    }

    public void buyLotto(final int money) {
        Ticket ticket = new Ticket(new Money(money));
        List<Lotto> lottos = generateLottoByTicket(ticket.getCount());
    }

    public List<Lotto> generateLottoByTicket(final int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottos.add(new Lotto(generator.get()));
        }
        return lottos;
    }

    public int calculateMoneyToTicket(final int money) {
        return money / 1000;
    }
}

