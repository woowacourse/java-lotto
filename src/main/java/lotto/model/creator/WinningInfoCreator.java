package lotto.model.creator;

import lotto.model.exception.WinningLottoBonusBallDuplicationException;
import lotto.model.object.BonusBall;
import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;
import lotto.model.object.WinningInfo;

import java.util.List;

public class WinningInfoCreator {
        public static WinningInfo create(Lotto winningLotto, BonusBall bonusBall) {
                checkWinningLottoBonusBallDuplication(winningLotto.getLottoNumbers(), bonusBall.getLottoNumber());
                return new WinningInfo(winningLotto, bonusBall);
        }

        private static void checkWinningLottoBonusBallDuplication(List<LottoNumber> lottoNumbers, LottoNumber lottoNumber) {
                if (lottoNumbers.contains(lottoNumber)) {
                        throw new WinningLottoBonusBallDuplicationException("당첨 번호와 보너스볼이 중복됩니다.");
                }
        }
}
