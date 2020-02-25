package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapSizeException;

public class Ticket {

    public static final int LOTTO_NUMBER_LENGTH = 6;
    public static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자가 아닙니다.";
    private static final String OVERLAP_SIZE_EXCEPTION = "중복된 숫자를 가지고 있습니다.";

    private List<LottoNumber> autoTicket;

    public Ticket(List<LottoNumber> ticket) {
        validateLottoNumbersLength(ticket);
        getValidateOverlap(ticket);
        autoTicket = ticket;
    }

    private void validateLottoNumbersLength(List<LottoNumber> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void getValidateOverlap(List<LottoNumber> ticket) {
        Set<LottoNumber> ticketValidation = new HashSet<>(ticket);
        if (ticketValidation.size() != 6) {
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
