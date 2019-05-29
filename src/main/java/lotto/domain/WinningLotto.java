package lotto.domain;

public class WinningLotto {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoTicket winningLottoTicket, int bonusBall) {
        this.winningLottoTicket = winningLottoTicket;
        this.bonusBall = LottoNumber.valueOf(bonusBall);
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (winningLottoTicket.hasBonusBall(bonusBall)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복 안됩니다.");
        }
    }

    public LottoRank getRank(LottoTicket lottoTicket) {
        int countOfMatch = winningLottoTicket.getSameCount(lottoTicket);
        boolean hasBonusBall = lottoTicket.hasBonusBall(bonusBall);
        return LottoRank.valueOf(countOfMatch,hasBonusBall);
    }
}
