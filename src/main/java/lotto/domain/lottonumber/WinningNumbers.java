package lotto.domain.lottonumber;

public class WinningNumbers {

    static final String WINNING_NUMBERS_CONTAIN_BONUS_BALL = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.";

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(String lottoNumberStrings, String bonusBallString) {
        if (isDuplicated(lottoNumberStrings, bonusBallString)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
        }

        this.lottoTicket = new LottoTicket(lottoNumberStrings);
        this.bonusBall = new LottoNumber(bonusBallString);
    }

    /**
     * 다시 작성해야 한다 !!
     * 틀린 메서드다
     */
    private boolean isDuplicated(String lottoNumberStrings, String bonusBallString) {
        return lottoNumberStrings.contains(bonusBallString);
    }

    public int getMatchCount(LottoTicket lottoTicket) {
        return (int) this.lottoTicket.lottoNumbers().stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public boolean doesMatchBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusBall);
    }
}
