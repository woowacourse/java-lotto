package lotto.domain.lottonumbers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class WinningNumbers extends LottoNumbers {

    static final String WINNING_NUMBERS_CONTAIN_BONUS_BALL = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.";

    private final LottoNumber bonusBall;

    private WinningNumbers(Set<LottoNumber> lottoNumbers, LottoNumber bonusBall) {
        super(lottoNumbers);
        this.bonusBall = bonusBall;
    }

    public static WinningNumbers of(Set<String> lottoNumberStrings, String bonusBallString) {
        if (lottoNumberStrings.contains(bonusBallString)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
        }

        Set<LottoNumber> lottoNumbers = lottoNumberStrings.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        LottoNumber lottoNumber = new LottoNumber(bonusBallString);

        return new WinningNumbers(lottoNumbers, lottoNumber);
    }

    public int countContaining(LottoTicket lottoTicket) {
        return (int) lottoNumbers.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public boolean containBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusBall);
    }
}
