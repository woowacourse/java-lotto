package domain;

public class WinningNumber {
    private static final String ERROR_DUPLICATED_NUMBER = "보너스 볼은 당첨 번호와 중복될 수 없습니다.";

    private final LottoTicket winningNumbers;
    private final LottoNumber bonusBall;

    public WinningNumber(LottoTicket winningNumbers, LottoNumber bonusBall) {
        this.winningNumbers = winningNumbers;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    public Rank calculateRank(LottoTicket lottoTicket) {
        long correctNumber = lottoTicket.numbers().stream()
                .filter(this.winningNumbers::contains)
                .count();
        boolean hasBonus = lottoTicket.contains(this.bonusBall);
        return Rank.from(correctNumber, hasBonus);
    }
}
