package lotto.model.ticket;

import lotto.model.ticket.number.LottoNumber;
import lotto.model.result.LottoRank;

public class WinningTicket {

    public static final String LOTTO_NUMBER_DUPLICATED_WITH_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복되어서는 안됩니다";
    private LottoTicket winningTicket;
    private LottoNumber bonusBall;

    public WinningTicket(LottoTicket lottoTicket, LottoNumber bonusBall) {
        validateDuplicated(lottoTicket, bonusBall);
        winningTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    private void validateDuplicated(LottoTicket lottoTicket, LottoNumber bonusBall) {
        if (lottoTicket.containsNumber(bonusBall)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_WITH_BONUS_BALL_MESSAGE);
        }
    }

    public LottoRank compare(LottoTicket lottoTicket) {
        int matchCount = lottoTicket.countMatch(winningTicket);
        boolean isBonusMatch = lottoTicket.containsNumber(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
