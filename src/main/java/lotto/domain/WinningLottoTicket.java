package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoTicket extends LottoTicket {
    private static final String DUPLICATION_NUMBER = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.";

    private final LottoNumber bonusBallNumber;

    private WinningLottoTicket(List<LottoNumber> lottoNumbers, LottoNumber bonusBallNumber) {
        super(lottoNumbers);
        this.bonusBallNumber = bonusBallNumber;
    }

    public static WinningLottoTicket of(List<Integer> winningNumbers, int bonusBallNumber) {
        if (winningNumbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(DUPLICATION_NUMBER);
        }
        List<LottoNumber> winningLottoNumber = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusLottoNumber = new LottoNumber(bonusBallNumber);
        return new WinningLottoTicket(winningLottoNumber, bonusLottoNumber);
    }
}
