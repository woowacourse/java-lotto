package domain;

public class WinningLotto {

    private static final String NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호는 당첨 번호와 달라야 합니다.";

    private final LottoTicket winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        validateNoDuplicateInList(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult getLottoResult(LottoTicket lottoTicket) {
        int matchCount = getMatchCount(lottoTicket);
        boolean hasBonus = containsBonus(lottoTicket);

        return LottoResult.of(matchCount, hasBonus);
    }

    private int getMatchCount(LottoTicket lottoTicket) {
        return (int) lottoTicket.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    private boolean containsBonus(LottoTicket lottoTicket) {
        return lottoTicket.getNumbers().contains(bonusNumber);
    }

    private void validateNoDuplicateInList(LottoTicket lottoTicket, LottoNumber target) {
        if (lottoTicket.getNumbers().stream().anyMatch(lottoNumber -> lottoNumber == target)) {
            throw new IllegalArgumentException(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
