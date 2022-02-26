package domain;

public class WinningLotto {
    private static final String ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 로또 번호와 중복될 수 없습니다 : ";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicateBonusNumber(lotto, bonusNumber);

        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER + bonusNumber.getNumber());
        }
    }

    public Rank getRankByLotto(Lotto otherLotto) {
        long sameNumberCount = lotto.getSameNumberCount(otherLotto);
        boolean containsBonusNumber = otherLotto.containsLottoNumber(bonusNumber);

        return Rank.of(sameNumberCount, containsBonusNumber);
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "lotto=" + lotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
