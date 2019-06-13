package lotto.domain;

import lotto.domain.generator.LottoManualGenerator;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private static final String DUPLICATE_MESSAGE = "보너스 볼이 중복 됩니다.";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> winningLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(String input, int userInputBonusNum) {
        this.winningLotto = LottoManualGenerator.generate(input);
        LottoNumber bonusNumber = LottoNumber.from(userInputBonusNum);
        checkDuplicate(bonusNumber);
        this.bonusBall = bonusNumber;
    }

    private void checkDuplicate(LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_MESSAGE);
        }
    }

    public int getWinningLottoValueByIndex(int index) {
        return winningLotto.get(index).getValue();
    }

    public int getBonusBallValue() {
        return bonusBall.getValue();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }

    Rank getCountOfMatch(Lotto lotto) {
        int count = 0;
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            count += lotto.isContainNumber(this.winningLotto.get(i)) ? 1 : 0;
        }
        Rank rank = Rank.valueOf(count, lotto.isContainNumber(bonusBall));
        return rank;
    }

}
