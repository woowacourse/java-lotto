package lotto.domain;

import lotto.exception.WinningNumbersException;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        validateDuplication(winningLottoTicket, bonusNumber);
        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.contains(bonusNumber)) {
            throw new WinningNumbersException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Rank> checkOutLottos(List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.checkOut(winningLottoTicket, bonusNumber))
                .filter(Rank::isValidRank)
                .collect(Collectors.toList());
    }
}
