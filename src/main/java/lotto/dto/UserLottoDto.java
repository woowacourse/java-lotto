package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.UserLotto;

import java.util.List;

public class UserLottoDto {
    private int round;
    private List<Lotto> userLotto;

    public UserLottoDto(int round, List<Lotto> userLotto) {
        this.round = round;
        this.userLotto = userLotto;
    }

    public int getRound() {
        return round;
    }

    public List<Lotto> getUserLotto() {
        return userLotto;
    }
}
