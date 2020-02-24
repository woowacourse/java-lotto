package lotto.domain;

public class WinningLotto {
    private static final String EMPTY_LOTTO_MSG = "로또 번호가 입력되지 않았습니다.";
    private static final String EMPTY_BONUS_MSG = "보너스 번호가 입력되지 않았습니다.";
    private static final String BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG = "보너스 번호가 당첨번호와 중복됩니다.";
    private Lotto lotto;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateEmpty(lotto, bonusNumber);
        validateDistinctBonus(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateEmpty(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto == null) {
            throw new IllegalArgumentException(EMPTY_LOTTO_MSG);
        }
        if (bonusNumber == null) {
            throw new IllegalArgumentException(EMPTY_BONUS_MSG);
        }
    }
    private void validateDistinctBonus(Lotto lotto, LottoNumber bonusNumber) {
        boolean bonusNumberMatchWithLottoNumbers = lotto.lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getNumber());
        if (bonusNumberMatchWithLottoNumbers) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG);
        }
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
