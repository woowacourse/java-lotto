package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final static List<List<LottoBall>> LottoTickets = new ArrayList<>();

    public LottoTickets(List<LottoBall> lottoTicket) {
        LottoTickets.add(lottoTicket);
    }

    public static List<List<LottoBall>> getLottoTickets() {
        return Collections.unmodifiableList(LottoTickets);
    }

}
