package domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoTicketsGenerator {

    public static List<LottoTicket> generateManualLottoTickets(List<String> input) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String manualLottoNumber = input.get(i);
            LottoTicket manualLottoTicket = ManualLottoTicketGenerator.generateManualLottoTicket(manualLottoNumber);
            manualLottoTickets.add(manualLottoTicket);
        }
        return manualLottoTickets;
    }
}
