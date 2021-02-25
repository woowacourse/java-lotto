package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> toList() {
        return new ArrayList<>(lottos);
    }

    public void addManuallyCreatedTickets(List<Lotto> manuallyCreatedLottos) {
        lottos.addAll(manuallyCreatedLottos);
    }

    public void generateTicketAutomatically(int counts) {
        for (int i = 0; i < counts; i++) {
            lottos.add(RandomNumberTicketFactory.makeTicket());
        }
    }
}
