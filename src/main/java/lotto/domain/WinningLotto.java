package lotto.domain;

import java.util.Objects;

public class WinningLotto {
    private static final String EMPTY_LOTTO_MSG = "로또 번호가 입력되지 않았습니다.";
    private static final String EMPTY_BONUS_MSG = "보너스 번호가 입력되지 않았습니다.";
    private static final String BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG = "보너스 번호가 당첨번호와 중복됩니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateNotNull(lotto, bonusNumber);
        validateDistinctBonus(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateNotNull(Lotto lotto, LottoNumber bonusNumber) {
        Objects.requireNonNull(lotto, EMPTY_LOTTO_MSG);
        Objects.requireNonNull(bonusNumber, EMPTY_BONUS_MSG);
    }

    private void validateDistinctBonus(Lotto lotto, LottoNumber bonusNumber) {
        boolean bonusNumberMatchWithLottoNumbers = lotto.hasLottoNumber(bonusNumber);

        if (bonusNumberMatchWithLottoNumbers) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG);
        }
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public int computeMatchCount(Lotto lotto) {
        long count = lotto.getLottoNumbers()
                .stream()
                .filter(this.lotto::hasLottoNumber)
                .count();
        return Math.toIntExact(count);
    }
}
