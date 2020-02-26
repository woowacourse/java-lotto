package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapSizeException;

public class Ticket {

    public static final int LOTTO_NUMBER_LENGTH = 6;
    public static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "숫자 6개를 입력해주세요.";
    private static final String OVERLAP_SIZE_EXCEPTION = "중복된 숫자를 가지고 있습니다.";

    private List<LottoNumber> autoTicket;

    public Ticket(List<LottoNumber> ticket) {
        validateLottoNumbersLength(ticket);
        validateOverlap(ticket);
        autoTicket = ticket;
    }

    private void validateLottoNumbersLength(List<LottoNumber> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateOverlap(List<LottoNumber> ticket) {
        Set<LottoNumber> ticketValidation = new HashSet<>(ticket);
        if (ticketValidation.size() != LOTTO_NUMBER_LENGTH) {
            throw new OverlapSizeException(OVERLAP_SIZE_EXCEPTION);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return autoTicket.contains(lottoNumber);
    }

    public List<LottoNumber> getTicket() {
        return autoTicket;
    }
}
