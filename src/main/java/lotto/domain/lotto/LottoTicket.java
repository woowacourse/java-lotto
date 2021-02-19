package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.number.Payout;

public class LottoTicket {

    private static final int LOTTO_PRICE = 1000;
    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(List<LottoNumbers> lottoTicket) {
        this.lottoTicket = new ArrayList<>(lottoTicket);
    }

    public static LottoTicket valueOf(Payout payout, LottoGenerator lottoGenerator) {
        return Stream.generate(lottoGenerator::nextLottoNumbers)
            .limit(payout.getNumberOfStuff(LOTTO_PRICE))
            .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    public int getCount() {
        return lottoTicket.size();
    }

    public int getTotalLottoPrice() {
        return lottoTicket.size() * LOTTO_PRICE;
    }

    public List<LottoNumbers> toLottoNumbersList() {
        return new ArrayList<>(lottoTicket);
    }
}