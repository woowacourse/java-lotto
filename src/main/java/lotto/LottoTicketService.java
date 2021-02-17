package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {

    private LottoTicketService() {
    }

    public static LottoTicket createLottoTicket() {
        List<LottoNumber> lottoNumbers = LottoNumber.getCache();
        Collections.shuffle(lottoNumbers);
        return new LottoTicket(
                lottoNumbers
                        .stream()
                        .limit(6)
                        .sorted()
                        .collect(Collectors.toList()));
    }

}
