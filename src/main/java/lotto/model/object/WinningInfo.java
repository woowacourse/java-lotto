package lotto.model.object;

import lotto.model.LottoRank;
import lotto.model.exception.WinningLottoBonusBallDuplicationException;

public class WinningInfo {
        private Lotto winningLotto;
        private BonusBall bonusBall;

        public WinningInfo(final Lotto winningLotto, final BonusBall bonusBall) {
                checkWinningLottoBonusBallDuplication(winningLotto, bonusBall);
                this.winningLotto = winningLotto;
                this.bonusBall = bonusBall;
        }

        private static void checkWinningLottoBonusBallDuplication(final Lotto winningLotto, final BonusBall bonusBall) {
                if (winningLotto.hasBonusBall(bonusBall)) {
                        throw new WinningLottoBonusBallDuplicationException("당첨 번호와 보너스볼이 중복됩니다.");
                }
        }

        public int getMatchNumber(final Lotto purchasedLotto) {
                return purchasedLotto.getMatchNumber(winningLotto);
        }

        public boolean hasBonusBallIn(final Lotto purchasedLotto) {
                return purchasedLotto.hasBonusBall(bonusBall);
        }

        public LottoRank getMatchRank(Lotto purchasedLotto) {
                int matchNumber = purchasedLotto.getMatchNumber(winningLotto);
                boolean hasBonusBall = purchasedLotto.hasBonusBall(bonusBall);
                return LottoRank.getLottoRank(matchNumber, hasBonusBall);
        }
}
