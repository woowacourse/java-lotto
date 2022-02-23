package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    private final Map<Integer, WinningPrize> winningInfo = new HashMap<>() {{
        put(6, WinningPrize.FIRST);
        put(5, WinningPrize.THIRD);
        put(4, WinningPrize.FOURTH);
        put(3, WinningPrize.FIFTH);
    }};
    private final List<LottoTicket> lottoTickets;

    public LottoGame(int purchaseMoney){
        lottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseMoney/1000; i++) {
            lottoTickets.add(new LottoTicket(new LottoNumberGenerateStrategy()));
        }
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
