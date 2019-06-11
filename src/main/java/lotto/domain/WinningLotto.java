package lotto.domain;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateWinningLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateWinningLotto(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼이 이미 입력된 로또 숫자와 중복됩니다.");
        }
    }

    Rank match(Lotto userLotto) {
        int countOfMatch = lotto.compareTo(userLotto);
        boolean matchBonus = userLotto.contains(bonusBall);

        return Rank.valueOf(countOfMatch, matchBonus);
    }
}
