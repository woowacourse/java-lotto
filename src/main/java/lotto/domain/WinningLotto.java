package lotto.domain;

import static lotto.util.NullValidator.validateNull;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateNull(lotto, bonusNumber);
        validateDuplicatedNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private static void validateDuplicatedNumber(Lotto winningLottoLine, LottoNumber bonusNumber) {
        if (winningLottoLine.contains(bonusNumber)) {
            throw new RuntimeException("당첨 번호와 중복되는 보너스 볼입니다.");
        }
    }

    private int countSameNumbers(Lotto lotto) {
        return (int) lotto.get()
                .stream()
                .filter(this.lotto::contains)
                .count();
    }

    public MatchResult createResult(Lotto lotto) {
        int matchCount = countSameNumbers(lotto);
        boolean containsBonusNumber = lotto.contains(bonusNumber);
        return MatchResult.of(matchCount, containsBonusNumber);
    }

    public boolean hasMatchResult(Lotto lotto) {
        int matchCount = countSameNumbers(lotto);
        return MatchResult.hasMatchCount(matchCount);
    }
}
