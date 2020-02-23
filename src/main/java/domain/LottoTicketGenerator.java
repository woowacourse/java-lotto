package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketGenerator {
    private LottoTicketGenerator() {
    }

    public static LottoTicket generateLottoTicket() {
        LottoNumbers.shuffleLottoNumbers();
        List<LottoNumber> lottoTicket = LottoNumbers.getInstance().stream()
                .limit(6)
                .collect(Collectors.toList());
        return new LottoTicket(lottoTicket);
    }
}
