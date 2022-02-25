package lotto.domain;

public class WinningNumber {

    private static final String DUPLICATED_WINNING_NUMBER_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";

    private final LottoTicket lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(LottoTicket lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        validateDuplicateBonusNumber(lottoNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(LottoTicket lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.isSame(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_WINNING_NUMBER_ERROR_MESSAGE);
        }
    }

    public Rank compare(LottoTicket lottoTicket) {
        if (lottoTicket.isSame(bonusNumber)) {
            return Rank.of(getCorrectCount(lottoTicket) + 1, true);
        }

        return Rank.of(getCorrectCount(lottoTicket), false);
    }

    private int getCorrectCount(LottoTicket lottoTicket) {
        return (int) lottoNumbers.getLottoNumbers()
                .stream()
                .filter(lottoTicket::isSame)
                .count();
    }
}
