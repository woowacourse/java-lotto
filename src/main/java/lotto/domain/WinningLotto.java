package lotto.domain;

public class WinningLotto {

    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다";
    private final Lotto winLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateBonusBall(lotto, bonusBall);
        this.winLotto = lotto;
        this.bonusBall = bonusBall;
    }

    public void validateBonusBall(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.isContain(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }

    private int howManyWins(Lotto lotto) {
        return lotto.getLottoNumbers()
            .stream().filter(winLotto::isContain)
            .toArray().length;
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = lotto.isContain(bonusBall);
        return Rank.makeRankByMatch(match, bonusMatch);
    }
}
