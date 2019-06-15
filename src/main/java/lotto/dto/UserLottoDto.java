package lotto.dto;

import lotto.domain.UserLotto;

public class UserLottoDto {
    private int round;
    private UserLotto userLotto;

    public UserLottoDto(int round, UserLotto userLotto) {
        this.round = round;
        this.userLotto = userLotto;
    }

    public int getRound() {
        return round;
    }

    public UserLotto getUserLotto() {
        return userLotto;
    }
}
