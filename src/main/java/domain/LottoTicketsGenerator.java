package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsGenerator {
    private LottoTicketsGenerator() {
    }

    public static List<LottoTicket> generateAutoLottoTickets(int autoLottoTicketCount) {
        List<LottoTicket> autoLottoTickets = new ArrayList<>();
        for (int i = 0; i < autoLottoTicketCount; i++) {
            autoLottoTickets.add(LottoTicketGenerator.generateAutoLottoTicket());
        }
        return autoLottoTickets;
    }

    public static List<LottoTicket> generateManualLottoTickets(List<String> input) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String manualLottoNumber = input.get(i);
            LottoTicket manualLottoTicket = LottoTicketGenerator.generateManualLottoTicket(manualLottoNumber);
            manualLottoTickets.add(manualLottoTicket);
        }
        return manualLottoTickets;
    }
}
