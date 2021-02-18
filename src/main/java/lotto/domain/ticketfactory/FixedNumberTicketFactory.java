package lotto.domain.ticketfactory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.LottoCustomException;

import java.util.Set;
import java.util.stream.Collectors;

public class FixedNumberTicketFactory {
    private static final int EXACT_SIZE = 6;

    private FixedNumberTicketFactory() {
    }

    public static LottoTicket makeTicket(Set<Integer> lottoNumbers) {
        if (!hasExactSize(lottoNumbers)) {
            throw new LottoCustomException("당첨번호는 서로 다른 숫자 6개이어야 합니다.");
        }
        return new LottoTicket(getFixedLottoNumbers(lottoNumbers));
    }

    public static Set<LottoNumber> getFixedLottoNumbers(Set<Integer> lottoNumbers) {
        return lottoNumbers
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    private static boolean hasExactSize(Set<Integer> lottoNumbers) {
        return lottoNumbers.size() == EXACT_SIZE;
    }
}
