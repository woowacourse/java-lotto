package lotto.model;

import java.util.List;

public class ManualTicket extends LottoTicket {
    public LottoTicket createManualTicket(List<Integer> manualTicket) {
        checkLottoLength(manualTicket);
        for (int number : manualTicket) {
            checkLottoNumberRange(number);
        }
        LottoTicket lottoTicket = new LottoTicket();
        lottoTicket.lottoTicket = manualTicket;
        return lottoTicket;
    }
}
