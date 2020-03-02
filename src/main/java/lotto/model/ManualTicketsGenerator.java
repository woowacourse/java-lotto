package lotto.model;

import java.util.ArrayList;
import java.util.List;
import lotto.view.InputView;

public class ManualTicketsGenerator implements TicketsGenerator {

    @Override
    public List<Ticket> generate(int manualCount) {
        List<Ticket> manualTickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualTickets.add(new Ticket(InputView.input()));
        }
        return manualTickets;
    }
}
