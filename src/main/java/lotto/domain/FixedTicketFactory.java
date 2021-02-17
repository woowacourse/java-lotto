package lotto.domain;

import java.util.Set;
import java.util.stream.Collectors;

public class FixedTicketFactory {

    public static LottoTicket makeTicket(Set<String> lottoNumbers) {
        return new LottoTicket(getFixedLottoNumbers(lottoNumbers));
    }

    public static Set<LottoNumber> getFixedLottoNumbers(Set<String> lottoNumbers) {
        return lottoNumbers
            .stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }
}
