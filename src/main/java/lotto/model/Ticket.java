package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapException;

public class Ticket {

    public static final int LOTTO_NUMBER_LENGTH = 6;
    public static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "숫자 6개를 입력해주세요.";
    private static final String OVERLAP_SIZE_EXCEPTION = "중복된 숫자를 가지고 있습니다.";

    private List<LottoNumber> ticket;

    public Ticket(List<LottoNumber> ticket) {
        validateLottoNumbersLength(ticket);
        validateOverlap(ticket);
        this.ticket = ticket;
    }

    public Ticket(String input) {
        List<LottoNumber> ticket = makeWinNumbers(splitInput(input));
        validateLottoNumbersLength(ticket);
        validateOverlap(ticket);
        this.ticket = ticket;
    }

    private void validateLottoNumbersLength(List<LottoNumber> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateOverlap(List<LottoNumber> ticket) {
        Set<LottoNumber> ticketValidation = new HashSet<>(ticket);
        if (ticketValidation.size() != LOTTO_NUMBER_LENGTH) {
            throw new OverlapException(OVERLAP_SIZE_EXCEPTION);
        }
    }

    private List<String> splitInput(String winNumber) {
        return Arrays.asList(winNumber.split(WinLottoNumbers.COMMA));
    }

    private List<LottoNumber> makeWinNumbers(List<String> inputs) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (String input : inputs) {
            numbers.add(new LottoNumber(validateNumberFormat(input)));
        }
        return numbers;
    }

    private int validateNumberFormat(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new NotNumberException(Money.NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return ticket.contains(lottoNumber);
    }

    public List<LottoNumber> getTicket() {
        return ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket1 = (Ticket) o;
        return Objects.equals(ticket, ticket1.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }
}
