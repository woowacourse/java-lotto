package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public class LottoDto {
    private int round;
    private int manualRound;
    private int autoRound;
    private List<Lotto> userLotto;

    public LottoDto(int round, int manualRound, int autoRound, List<Lotto> userLotto) {
        this.round = round;
        this.manualRound = manualRound;
        this.autoRound = autoRound;
        this.userLotto = userLotto;
    }

    public int getRound() {
        return round;
    }

    public int getManualRound() {
        return manualRound;
    }

    public int getAutoRound() {
        return autoRound;
    }

    public List<Lotto> getUserLotto() {
        return userLotto;
    }
}
