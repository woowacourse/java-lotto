package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.UserLotto;

import java.util.List;

public class UserLottoDto {
    private final int lottoRound;
    private final int round;
    private final List<Lotto> userLotto;

    public UserLottoDto(int round, List<Lotto> userLotto, int lottoRound) {
        this.round = round;
        this.userLotto = userLotto;
        this.lottoRound = lottoRound;
    }

    public int getRound() {
        return round;
    }

    public List<Lotto> getUserLotto() {
        return userLotto;
    }

    public int getLottoRound() {
        return lottoRound;
    }
}
