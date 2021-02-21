package lotto.domain.ticketFactory;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_MAX_LIMIT;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_MIN_LIMIT;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class TicketFactory {

    static final RandomTicketGenerator randomTicketGenerator;

    static {
        final List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = LOTTO_NUMBER_MIN_LIMIT; i <= LOTTO_NUMBER_MAX_LIMIT; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        randomTicketGenerator = new RandomTicketGenerator(lottoNumbers);
    }

    private TicketFactory() { }

    public static LottoTicket makeFixedTicket(List<Integer> lottoNumbers) {
        FixedTicketGenerator fixedTicketGenerator = new FixedTicketGenerator(lottoNumbers);
        return fixedTicketGenerator.generateTicket();
    }

    public static LottoTickets makeRandomTicketsByCount(int counts) {
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < counts; i++) {
            lottoTickets.addTicket(randomTicketGenerator.generateTicket());
        }
        return lottoTickets;
    }
}
