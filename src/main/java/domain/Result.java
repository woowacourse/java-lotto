package domain;

import java.util.List;

public class Result {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public Result(final List<Integer> winningNumbers, final int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        winningTicket = LottoTicket.generateManual(winningNumbers);
        this.bonusNumber = LottoNumber.of(bonusNumber);
    }

    private void validate(final List<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호에 보너스 번호가 포함되어 있습니다.");
        }
    }

    public Ranks calculate(final LottoTickets lottoTickets) {
        return lottoTickets.checkMatch(winningTicket, bonusNumber);
    }
}