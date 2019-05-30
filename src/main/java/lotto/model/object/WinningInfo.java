package lotto.model.object;

public class WinningInfo {
        private Lotto winningLotto;
        private BonusBall bonusBall;


        public WinningInfo(final Lotto winningLotto, final BonusBall bonusBall) {
                this.winningLotto = winningLotto;
                this.bonusBall = bonusBall;
        }

        public int getMatchNumber(final Lotto purchasedLotto) {
                return purchasedLotto.getMatchNumber(winningLotto);
        }

        public boolean hasBonusBallIn(final Lotto purchasedLotto) {
                return purchasedLotto.hasBonusBall(bonusBall);
        }
}
