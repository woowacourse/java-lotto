package lotto.domain.lottonumber;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WinningNumbers {

    static final String DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.";

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(LottoTicket lottoTicket, LottoNumber bonusBall) {
        if (isDuplicated(lottoTicket, bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL);
        }

        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    public WinningNumbers(String lottoNumbersString, String bonusBallsString) {
        if (isDuplicated(lottoNumbersString, bonusBallsString)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL);
        }

        lottoTicket = new LottoTicket(lottoNumbersString);
        bonusBall = new LottoNumber(bonusBallsString);
    }

    private boolean isDuplicated(LottoTicket lottoTicket, LottoNumber bonusBall) {
        return lottoTicket.contains(bonusBall);
    }

    private boolean isDuplicated(String lottoNumbersString, String bonusBallsString) {
        return Arrays.stream(lottoNumbersString.split(","))
                .map(String::trim)
                .collect(Collectors.toList())
                .contains(bonusBallsString.trim());
    }

    public int getMatchCount(LottoTicket lottoTicket) {
        return this.lottoTicket.getMatchCount(lottoTicket);
    }

    public boolean doesMatchBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusBall);
    }
}
