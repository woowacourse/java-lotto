package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketFactory {
    private final List<LottoNumber> lottoNumberRange;

    public LottoTicketFactory() {
        this.lottoNumberRange = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            this.lottoNumberRange.add(new LottoNumber(Integer.toString(i)));
        }
    }

    public LottoTickets buyLottoTickets(Money money) {
        int length = (int) money.getValue() / 1000;
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Collections.shuffle(lottoNumberRange);
            List<LottoNumber> lottoNumbers = lottoNumberRange.subList(0, 6);
            lottoTickets.add(new LottoTicket(lottoNumbers));
        }
        return new LottoTickets(lottoTickets);
    }
}
