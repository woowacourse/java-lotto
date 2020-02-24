package lotto.view.dto;

import lotto.exception.ConvertFailException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinLottoTicketDTO {
    private static final String PARSE_FAIL_EXCEPTION_MESSAGE = "%s : 숫자가 아닌 문자가 존재합니다.";
    private static final String COMMA = ",";

    private final List<Integer> winNumbers;
    private final int bonusNumber;

    public WinLottoTicketDTO(String winNumbers, int bonusNumber) {
        this.winNumbers = collectNumber(winNumbers);
        this.bonusNumber = bonusNumber;
    }

    private List<Integer> collectNumber(String winningNumbers) {
        try {
            return splitAndConvertToInteger(winningNumbers);
        } catch (NumberFormatException e) {
            throw new ConvertFailException(String.format(PARSE_FAIL_EXCEPTION_MESSAGE, winningNumbers));
        }
    }

    private List<Integer> splitAndConvertToInteger(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(COMMA))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
