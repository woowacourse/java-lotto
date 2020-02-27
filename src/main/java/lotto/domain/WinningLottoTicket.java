package lotto.domain;

import lotto.domain.errors.ErrorMessage;

import java.util.List;

public class WinningLottoTicket extends LottoTicket {
    private LottoNumber bonusNumber;

    public WinningLottoTicket(final List<LottoNumber> winningNumbers, final LottoNumber bonusNumber) {
        super(winningNumbers);
        validateDistinctBonus(super.lottoNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static void validateDistinctBonus(final List<LottoNumber> lottoNumbers, final LottoNumber bonusNumber) {
        if (isBonusNumberAlreadyExist(lottoNumbers, bonusNumber)) {
            ErrorMessage nowErrorMessage = ErrorMessage.DUPLICATE_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private static boolean isBonusNumberAlreadyExist(final List<LottoNumber> lottoNumbers, final LottoNumber inputBonusNumber) {
        return lottoNumbers.stream()
                .anyMatch(inputBonusNumber::equals);
    }

    public boolean isBonusMatched(final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .anyMatch(bonusNumber::equals);
    }

    public long countMatchedNumber(final WinningLottoTicket winningLotto, final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .filter(winningLotto::isMatched)
                .count();
    }

    private boolean isMatched(final LottoNumber userLottoNumber) {
        return lottoNumbers.contains(userLottoNumber);
    }
}
