package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;
    private static final List<LottoGenerator> lottoGenerators = Arrays.asList(new AutoTicket(), new ManualTicket());
    public LottoTickets() {
        lottoTickets = new ArrayList<>();
    }

    public void combineTickets(TicketInformation ticketInformation) {
        for (LottoGenerator lotto : lottoGenerators) {
            lottoTickets.addAll(lotto.generate(ticketInformation));
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public LottoResult matchLottoResult(WinNumber winNumber, BonusBall bonusBall) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            lottoResult.checkCount(lottoTicket, winNumber, bonusBall);
        }
        return lottoResult;
    }
}
