package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {

    public static List<Ticket> create(List<String> manualTickets, LottoCount lottoCount) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(getManualTickets(manualTickets, lottoCount));
        tickets.addAll(getAutoTickets(lottoCount));
        return tickets;
    }

    private static List<Ticket> getManualTickets(List<String> manualTickets, LottoCount lottoCount) {
        ManualTicketsGenerator manualTicketsGenerator = new ManualTicketsGenerator(manualTickets);
        return manualTicketsGenerator.generate(lottoCount);
    }

    private static List<Ticket> getAutoTickets(LottoCount lottoCount) {
        AutoTicketsGenerator autoTicketsGenerator = new AutoTicketsGenerator();
        return autoTicketsGenerator.generate(lottoCount);
    }
}
