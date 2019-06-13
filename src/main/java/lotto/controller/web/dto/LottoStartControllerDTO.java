package lotto.controller.web.dto;

import lotto.domain.*;
import lotto.domain.vo.LottoResult_VO;

public class LottoStartControllerDTO {
    private Price price;
    private NumberOfCustomLotto customAmount;
    private LottoTicket lottoTicket;
    private WinningLotto winningLotto;
    private LottoResult_VO result;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public NumberOfCustomLotto getCustomAmount() {
        return customAmount;
    }

    public void setCustomAmount(NumberOfCustomLotto customAmount) {
        this.customAmount = customAmount;
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }

    public void setLottoTicket(LottoTicket lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public LottoResult_VO getLottoResult() {
        return result;
    }

    public void setLottoResult(LottoResult_VO result) {
        this.result = result;
    }
}
