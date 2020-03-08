package domain;

import java.util.List;

public class WinningLottoTicket {
    private LottoTicket winningLottoTicket;
    private LottoNumber bonusBall;

    public WinningLottoTicket(LottoTicket lottoTicket, LottoNumber bonusBall) {
        this.winningLottoTicket = lottoTicket;
        initializeBonusBall(bonusBall);
    }

    private void initializeBonusBall(LottoNumber bonusBall) {
        if (this.winningLottoTicket.containLottoNumber(bonusBall)) {
            throw new IllegalArgumentException("보너스 로또 숫자는 당첨 숫자와 중복될 수 없습니다.");
        }
        this.bonusBall = bonusBall;
    }

    public List<LottoNumber> getWinningLottoTicket() {
        return this.winningLottoTicket.getLottoTicket();
    }

    public LottoNumber getBonusNumber() {
        return this.bonusBall;
    }
}
