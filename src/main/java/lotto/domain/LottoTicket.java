package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    List<LottoBall> lottoTicket = new ArrayList<>();

    public LottoTicket(List<LottoBall> lottoTicket){
        //NUll 예외처리 하기

        this.lottoTicket = lottoTicket;
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
