package lotto.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapWinNumberException;

public class WinLottoNumbers {

    public static final String COMMA = ",";
    private static final String IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE = "당첨번호와 중복되는 숫자가 있습니다.";

    private Ticket winLottoNumbers;
    private LottoNumber bonusBallNumber;

    public WinLottoNumbers(String winNumber, String bonusBall) {
        List<LottoNumber> winNumbers = makeWinNumbers(splitInput(winNumber));
        validateLottoNumbersLength(winNumbers);
        this.winLottoNumbers = new Ticket(winNumbers);

        int bonusBallNumber = validateNumberFormat(bonusBall);
        validateContainsWinNumber(bonusBallNumber);
        this.bonusBallNumber = new LottoNumber(bonusBallNumber);
    }

    private List<String> splitInput(String winNumber) {
        return Arrays.asList(winNumber.split(COMMA));
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

    private void validateLottoNumbersLength(List<LottoNumber> inputs) {
        if (inputs.size() != Ticket.LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(Ticket.LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateContainsWinNumber(int bonusBallNumber) {
        if (winLottoNumbers.contains(LottoNumber.getLottoNumber(bonusBallNumber))) {
            throw new OverlapWinNumberException(IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int matchCount(Ticket ticket) {
        return (int) ticket.getTicket().stream().filter(x -> winLottoNumbers.contains(x))
            .count();
    }

    public LottoNumber getBonusBallNumber() {
        return bonusBallNumber;
    }
}