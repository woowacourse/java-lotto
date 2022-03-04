package lotto.domain.lottonumber;

import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.domain.matchkind.WinningKind;

import java.util.List;

public class WinningNumbers {
    private static final String TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE =
            "당첨 번호와 보너스 번호에 중복이 있으면 안됩니다.";

    private final Lotto lastWinningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(final List<Integer> lastWinningLotto, final int bonusNumber) {
        validateDuplication(lastWinningLotto, bonusNumber);
        this.lastWinningLotto = new Lotto(lastWinningLotto);
        this.bonusNumber = LottoNumber.from(bonusNumber);
    }

    private void validateDuplication(final List<Integer> winningLotto, final int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE);
        }
    }

    public WinningKind getLottoMatchResult(final Lotto anotherLotto) {
        final int matchedCount = anotherLotto.getMatchCount(lastWinningLotto);
        return WinningKind.from(matchedCount, anotherLotto.hasSameNumberWith(bonusNumber));
    }
}
