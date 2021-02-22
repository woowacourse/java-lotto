package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private static final int LOTTO_PRICE = 1000;
    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(List<LottoNumbers> lottoTicket) {
        this.lottoTicket = new ArrayList<>(lottoTicket);
    }

    public List<LottoNumbers> unwrap() {
        return new ArrayList<>(lottoTicket);
    }

    public static int getLottoPrice() {
        return LOTTO_PRICE;
    }

    public int getCount() {
        return lottoTicket.size();
    }

    public int getTotalLottoPrice() {
        return lottoTicket.size() * LOTTO_PRICE;
    }
}