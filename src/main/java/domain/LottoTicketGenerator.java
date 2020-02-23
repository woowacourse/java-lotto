package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketGenerator {
    private static final int LOTTO_TICKET_SIZE = 6;

    private LottoTicketGenerator() {
    }

    public static LottoTicket generateLottoTicket() {
        LottoNumbers.shuffleLottoNumbers();
        List<LottoNumber> lottoTicket = LottoNumbers.getInstance().stream()
                .limit(LOTTO_TICKET_SIZE)
                .collect(Collectors.toList());
        Collections.sort(lottoTicket);
        return new LottoTicket(lottoTicket);
    }
}
