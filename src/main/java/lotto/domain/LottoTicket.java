package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket){
        //NUll 예외처리 하기
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
