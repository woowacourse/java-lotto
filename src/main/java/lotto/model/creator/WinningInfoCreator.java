package lotto.model.creator;

import lotto.model.exception.WinningLottoBonusBallDuplicationException;
import lotto.model.object.BonusBall;
import lotto.model.object.Lotto;
import lotto.model.object.WinningInfo;

public class WinningInfoCreator {
        public static WinningInfo create(final Lotto winningLotto, final BonusBall bonusBall) {
                checkWinningLottoBonusBallDuplication(winningLotto, bonusBall);
                return new WinningInfo(winningLotto, bonusBall);
        }

        private static void checkWinningLottoBonusBallDuplication(final Lotto winningLotto, final BonusBall bonusBall) {
                if (winningLotto.hasBonusBall(bonusBall)) {
                        throw new WinningLottoBonusBallDuplicationException("당첨 번호와 보너스볼이 중복됩니다.");
                }
        }
}
