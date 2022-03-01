package lotto.domain.lottonumber;

public class WinningNumbers {

    static final String DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.";

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(String lottoNumberStrings, String bonusBallString) {
        lottoTicket = new LottoTicket(lottoNumberStrings);
        bonusBall = new LottoNumber(bonusBallString);

        if (isDuplicated(lottoTicket, bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL);
        }
    }

    private boolean isDuplicated(LottoTicket lottoTicket, LottoNumber bonusBall) {
        return lottoTicket.lottoNumbers().contains(bonusBall);
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
