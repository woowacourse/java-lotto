package domain;

/**
 * WinningNumber.java
 * 당첨 번호 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class WinningNumbers {
    private static final String ERROR_DUPLICATED_NUMBER = "[ERROR] 보너스 볼은 당첨 번호와 중복될 수 없습니다.";

    private final LottoTicket winningNumbers;
    private final LottoNumber bonusBall;

    private WinningNumbers(LottoTicket winningNumbers, LottoNumber bonusBall) {
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public static WinningNumbers of(LottoTicket winningNumbers, LottoNumber bonusBall) {
        validate(winningNumbers, bonusBall);
        return new WinningNumbers(winningNumbers, bonusBall);
    }

    private static void validate(LottoTicket winningNumbers, LottoNumber bonusBall) {
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
