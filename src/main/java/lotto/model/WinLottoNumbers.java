package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverlapWinNumberException;

public class WinLottoNumbers {

    private static final String IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE = "당첨번호와 중복되는 숫자가 있습니다.";

    private Ticket winLottoNumbers;
    private LottoNumber bonusBallNumber;

    public WinLottoNumbers(String winNumber, String bonusBall) {
        this.winLottoNumbers = new Ticket(winNumber);

        int bonusBallNumber = validateNumberFormat(bonusBall);
        validateContainsWinNumber(bonusBallNumber);
        this.bonusBallNumber = new LottoNumber(bonusBallNumber);
    }

    private int validateNumberFormat(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new NotNumberException(Money.NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void validateContainsWinNumber(int bonusBallNumber) {
        if (winLottoNumbers.contains(LottoNumber.getLottoNumber(bonusBallNumber))) {
            throw new OverlapWinNumberException(IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int matchCount(Ticket ticket) {
        return (int) ticket.getTicket().stream()
            .filter(winLottoNumbers::contains)
            .count();
    }

    public LottoNumber getBonusBallNumber() {
        return bonusBallNumber;
    }
}