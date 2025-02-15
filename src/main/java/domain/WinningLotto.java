package domain;

import java.util.List;

public class WinningLotto {
    private static final String ERROR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto winningLotto;
    private final int bonusNumber;

    private WinningLotto(final Lotto lotto, final int bonusNumber) {
        validate(lotto, bonusNumber);
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Lotto lotto, final int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public static WinningLotto of(final Lotto lotto, final int bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public List<Prize> calculatePrizes(Lottos lottos) {
        List<Lotto> purchasedLottos = lottos.getLottos();
        return purchasedLottos.stream()
                .map(lotto -> Prize.getPrizePlace(countMatchedNumbers(lotto), isBounusMatched(lotto)))
                .toList();
    }

    private int countMatchedNumbers(Lotto lotto) {
        return winningLotto.match(lotto);
    }

    private boolean isBounusMatched(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
