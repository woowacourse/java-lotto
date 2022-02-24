package model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public WinningStatistics winningResult(final LottoTickets lottoTickets) {
        Map<WinningRank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(WinningRank.values()).forEach(winningRank -> result.put(winningRank, 0));

        for (LottoTicket lottoTicket : lottoTickets.tickets()) {
            WinningRank key = lottoTicket.findRank(winningNumbers(), bonusNumber());
            result.put(key, result.get(key) + 1);
        }
        return new WinningStatistics(result);
    }

    private Integer bonusNumber() {
        return bonusNumber.getNumber();
    }

    private List<Integer> winningNumbers() {
        return winningNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList());
    }
}
