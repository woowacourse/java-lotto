package model.winning;

import java.util.List;
import java.util.stream.Collectors;
import model.lottotickets.vo.LottoNumber;

public class WinningNumbers {
    private static final int TICKET_SIZE = 6;
    private static final String NOT_CORRECT_TICKET_SIZE_ERROR_MESSAGE = "입력한 당첨 번호가 6개가 아닙니다.";

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        checkSize(winningNumbers);
        this.winningNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void checkSize(final List<Integer> winningNumbers) {
        if (winningNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException(NOT_CORRECT_TICKET_SIZE_ERROR_MESSAGE);
        }
    }

    public Integer bonusNumber() {
        return bonusNumber.get();
    }

    public List<Integer> winningNumbers() {
        return winningNumbers.stream().map(LottoNumber::get).collect(Collectors.toList());
    }
}
