package domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(final Lotto lotto, final LottoNumber bonusNumber) {
        validate(lotto, bonusNumber);
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Lotto lotto, final LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public static WinningLotto of(final Lotto lotto, final LottoNumber bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public List<Prize> calculatePrizes(Lottos lottos) {
        List<Lotto> purchasedLottos = lottos.getLottos();
        return purchasedLottos.stream()
                .map(lotto -> Prize.getPrizePlace(countMatchedNumbers(lotto), isBonusMatched(lotto)))
                .toList();
    }

    private int countMatchedNumbers(Lotto lotto) {
        return winningLotto.match(lotto);
    }

    private boolean isBonusMatched(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
