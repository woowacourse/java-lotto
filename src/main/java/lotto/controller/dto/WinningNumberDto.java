package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningNumber;

public class WinningNumberDto {

    private final List<Integer> normalNumbers;
    private final int bonusNumber;

    private WinningNumberDto(WinningNumber winningNumber) {
        this.normalNumbers = winningNumber.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .collect(toList());

        this.bonusNumber = winningNumber.getBonusNumber().getLottoNumber();
    }

    public static WinningNumberDto from(WinningNumber winningNumber) {
        return new WinningNumberDto(winningNumber);
    }

    public WinningNumber toWinningNumber() {
        List<LottoNumber> lottoNumbers = normalNumbers.stream()
                .map(LottoNumber::of)
                .collect(toList());

        return new WinningNumber(new LottoTicket(lottoNumbers), LottoNumber.of(bonusNumber));
    }
}
