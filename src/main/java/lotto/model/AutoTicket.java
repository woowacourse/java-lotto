package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTicket extends LottoTicket {

    public LottoTicket createAutoTicket() {
        List<Integer> autoTicket = new ArrayList<>();
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            autoTicket.add(i);
        }
        Collections.shuffle(autoTicket);
        autoTicket = autoTicket.subList(FIRST_INDEX, LOTTO_NUMBER_LENGTH);
        Collections.sort(autoTicket);
        LottoTicket lottoTicket = new LottoTicket();
        lottoTicket.lottoTicket = autoTicket;
        return lottoTicket;
    }
}
