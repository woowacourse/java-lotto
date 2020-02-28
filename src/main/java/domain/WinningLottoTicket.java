package domain;

import spark.utils.StringUtils;
import java.util.List;

public class WinningLottoTicket {
    private LottoTicket winningLottoTicket;
    private LottoNumber bonusBall;

    public WinningLottoTicket(String input) {
        validateBlank(input);
        List<LottoNumber> lottoNumbers = LottoNumberSplit.initializeLottoNumbers(input);
        this.winningLottoTicket = new LottoTicket(lottoNumbers);
    }

    private void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    public void initializeBonusBall(LottoNumber bonusNumber) {
        if (this.winningLottoTicket.containLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 로또 숫자는 당첨 숫자와 중복될 수 없습니다.");
        }
        this.bonusBall = bonusNumber;
    }

    public List<LottoNumber> getWinningLottoTicket() {
        return this.winningLottoTicket.getLottoTicket();
    }

    public LottoNumber getBonusNumber() {
        return this.bonusBall;
    }
}
