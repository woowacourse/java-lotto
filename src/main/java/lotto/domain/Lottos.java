package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos buyTicketsByAuto(Money money) {
        int ticketCount = getTicketCount(money);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottos.add(Lotto.getLottoByAuto());
        }
        return new Lottos(lottos);
    }

    private static int getTicketCount(Money money) {
        return money.getMoney() / LOTTO_TICKET_PRICE;
    }

    public List<Lotto> getTickets() {
        return Collections.unmodifiableList(lottos);
    }
}
