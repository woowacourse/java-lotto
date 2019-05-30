package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private Lotto lotto;
    private BonusNumber bonusNumber;

    public WinningLotto(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }

        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Result produceResult(LottoTickets lottoTickets) {
        Map<Rank, Integer> map = new HashMap<>();
        List<Lotto> tickets = lottoTickets.getLottoTickets();

        for (Lotto ticket : tickets) {
            produceResultForEachTicket(map, ticket);
        }

        return new Result(map);
    }

    private void produceResultForEachTicket(Map<Rank, Integer> map, Lotto ticket) {
        Rank rank = Rank.valueOf(ticket.match(lotto), lotto.contains(bonusNumber));
        if (map.containsKey(rank)) {
            map.put(rank, map.get(rank) + 1);
            return;
        }

        map.put(rank, 1);
    }
}
