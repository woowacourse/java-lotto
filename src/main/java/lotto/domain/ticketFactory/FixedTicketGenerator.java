package lotto.domain.ticketFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.LottoCustomException;

public class FixedTicketGenerator implements TicketGenerator {

    private final Set<Integer> fixedLottoNumbers;
    private static final int LOTTO_SIZE = 6;
    private static final String NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE = "당첨번호는 중복되지 않은 숫자들로 총 " + LOTTO_SIZE + "개이어야 합니다.";


    public FixedTicketGenerator(List<Integer> lottoNumbers) {
        this.fixedLottoNumbers = new HashSet<>(lottoNumbers);
    }

    @Override
    public LottoTicket generateTicket() {
        if (!isProperSize(fixedLottoNumbers)) {
            throw new LottoCustomException(NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE);
        }
        return new LottoTicket(fixedLottoNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet()));
    }

    private boolean isProperSize(Set<Integer> lottoNumbers) {
        return lottoNumbers.size() == LOTTO_SIZE;
    }
}
