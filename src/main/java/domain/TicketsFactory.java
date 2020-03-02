package domain;

import java.util.List;

public class TicketsFactory {
    public static Tickets getTickets(Object object) {
        TicketsCreator ticketsCreator;
        if (object instanceof Integer) {
            ticketsCreator = new AutoLottoTicketsCreator();
            return ticketsCreator.create(object);
        }
        ticketsCreator = new ManualLottoTicketsCreator();
        return ticketsCreator.create(object);
    }
}

class ManualLottoTicketsCreator implements TicketsCreator {
    @Override
    public Tickets create(Object object) {
        List<String> ticketsNumbers = (List<String>) object;
        Tickets manualLottoTickets = new ManualLottoTickets(
                ManualLottoTicketsGenerator.generateManualLottoTickets(ticketsNumbers)
        );
        return manualLottoTickets;
    }
}

class AutoLottoTicketsCreator implements TicketsCreator {
    @Override
    public Tickets create(Object object) {
        Integer autoLottoTicketsCount = (Integer) object;
        Tickets autoLottoTickets = new AutoLottoTickets(
                AutoLottoTicketsGenerator.generateAutoLottoTickets(autoLottoTicketsCount)
        );
        return autoLottoTickets;
    }
}
