package domain.lottoresult;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoTicket;

import java.util.Objects;

public class LottoWinner {
    private static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";
    private static final String ERROR_BONUS_MESSAGE = "보너스 숫자가 중복되었습니다.";

    private LottoTicket lottoTicket;
    private LottoNumber bonus;

    public LottoWinner(LottoTicket lottoTicket, LottoNumber bonus) {
        validateNullValue(lottoTicket, bonus);
        validateBonus(lottoTicket, bonus);
        this.lottoTicket = lottoTicket;
        this.bonus = bonus;
    }

    private void validateBonus(LottoTicket lottoTicket, LottoNumber bonus) {
        if (lottoTicket.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_BONUS_MESSAGE);
        }
    }

    private void validateNullValue(LottoTicket lottoTicket, LottoNumber bonus) {
        if (Objects.isNull(lottoTicket) || Objects.isNull(bonus)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public LottoRank createRank(LottoTicket lottoTicket) {
        int result = this.lottoTicket.calculateMatchNumber(lottoTicket);
        return LottoRank.calculateRank(result, lottoTicket.contains(bonus));
    }
}
