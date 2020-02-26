package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;

public class ManualLottoNumbers {
    private Ticket manualLottoNumbers;

    public ManualLottoNumbers(String input) {
        List<LottoNumber> manualLottoNumbers = makeWinNumbers(splitInput(input));
        validateLottoNumbersLength(manualLottoNumbers);
        this.manualLottoNumbers = new Ticket(manualLottoNumbers);
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

    private void validateLottoNumbersLength(List<LottoNumber> inputs) {
        if (inputs.size() != Ticket.LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(Ticket.LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
