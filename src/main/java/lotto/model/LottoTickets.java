package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets = new ArrayList<>();

    public LottoTickets(int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            lottoTickets.add(new LottoTicket());
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
