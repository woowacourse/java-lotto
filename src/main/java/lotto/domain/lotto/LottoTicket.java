package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(List<LottoNumbers> lottoTicket) {
        this.lottoTicket = new ArrayList<>(lottoTicket);
    }

    public int getCount() {
        return lottoTicket.size();
    }

    public List<LottoNumbers> toLottoNumbersList() {
        return new ArrayList<>(lottoTicket);
    }
}