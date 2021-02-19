package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MIN_LOTTO_NUMBER;
import static lotto.domain.LottoTicket.LOTTO_TICKET_SIZE;
import static lotto.domain.Money.LOTTO_PRICE;

public class LottoTicketFactory {
    public static final int START_INDEX = 0;

    private final List<LottoNumber> lottoNumberRange;

    public LottoTicketFactory() {
        this.lottoNumberRange = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            this.lottoNumberRange.add(new LottoNumber(Integer.toString(i)));
        }
    }

    public LottoTickets buyLottoTickets(Money money) {
        int length = (int) money.getValue() / LOTTO_PRICE;
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            lottoTickets.add(new LottoTicket(createLottoTicketByRange()));
        }
        return new LottoTickets(lottoTickets);
    }

    private List<LottoNumber> createLottoTicketByRange() {
        Collections.shuffle(lottoNumberRange);
        List<LottoNumber> lottoNumbers = lottoNumberRange.subList(START_INDEX, LOTTO_TICKET_SIZE);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
