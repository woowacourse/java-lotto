package lotto.domain.ticketFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class TicketFactory {

    public static final int LOTTO_NUMBER_MAX_LIMIT = 44;
    public static final int LOTTO_NUMBER_MIN_LIMIT = 1;
    public static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    private final RandomNumbersGenerator randomNumbersGenerator;

    public TicketFactory() {
        for (int i = LOTTO_NUMBER_MIN_LIMIT; i <= LOTTO_NUMBER_MAX_LIMIT; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        randomNumbersGenerator = new RandomNumbersGenerator();
    }

    public LottoTickets makeTicketsByCount(int counts) {
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < counts; i++) {
            lottoTickets.addTicket(makeTicket(randomNumbersGenerator.generateNumbers()));
        }
        return lottoTickets;
    }

    public LottoTicket makeTicket(Set<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }
}
