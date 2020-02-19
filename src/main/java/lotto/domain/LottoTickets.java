package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets createLottoTickets(int buyingQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < buyingQuantity; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }

    // TODO: 리팩토링 (또는 객체화)
    public Map<Integer, Integer> matchResult(LottoTicket winnerTicket, LottoNumber bonusNumber) {
        int match;
        Map<Integer, Integer> matchResult = new HashMap<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            match = winnerTicket.compare(lottoTicket);
            if (match == 5 && lottoTicket.contains(bonusNumber)) {
                matchResult.put(-1, matchResult.getOrDefault(-1, 0) + 1);
                continue;
            }
            matchResult.put(match, matchResult.getOrDefault(match, 0) + 1);
        }
        return matchResult;
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining("\n"));
    }
}
