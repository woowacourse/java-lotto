package domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoTicketsGenerator {

    List<String> manualLottoTickets;

    public ManualLottoGenerator(List<String> manualLottoTickets) {
        this.manualLottoTickets = manualLottoTickets;
    }

    private List<LottoTicket> createManualLottoTickets(List<String> manualLottoTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (String manualLottoTicket : manualLottoTickets) {
            lottoTickets.add(LottoTicketGenerator.createLottoTicket(manualLottoTicket));
        }

        return lottoTickets;
    }


    @Override
    public List<LottoTicket> generate() {
        return createManualLottoTickets(manualLottoTickets);
    }
}
