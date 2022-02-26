package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningNumber;

public class WinningNumberDto {

    private final List<Integer> normalNumbers;
    private final int bonusNumber;

    public WinningNumberDto(WinningNumber winningNumber) {
        this.normalNumbers = winningNumber.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .collect(toList());

        this.bonusNumber = winningNumber.getBonusNumber().getLottoNumber();
    }

    public WinningNumber toWinningNumber() {
        return new WinningNumber(new LottoTicket(normalNumbers), new LottoNumber(bonusNumber));
    }
}
