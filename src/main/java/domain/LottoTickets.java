package domain;

import java.util.*;

public class LottoTickets {
    private static final Integer INITIAL_VALUE_ONE = 1;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets of(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    public void add(LottoTicket lottoTicket) {
        this.lottoTickets.add(lottoTicket);
    }

    public void addAll(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.lottoTickets());
    }

    public LottoResult generateLottoResult(WinningNumbers winningNumbers)  {
        Map<Rank, Integer> resultStatisticsMap = new HashMap<>();
        for (LottoTicket lottoTicket : this.lottoTickets) {
            Rank rank = winningNumbers.calculateRank(lottoTicket);
            addCount(resultStatisticsMap, rank);
        }
        return new LottoResult(resultStatisticsMap);
    }

    private static void addCount(Map<Rank, Integer> resultStatisticsMap, Rank rank) {
        if (resultStatisticsMap.containsKey(rank)) {
            int count = resultStatisticsMap.get(rank);
            resultStatisticsMap.put(rank, count + 1);
            return;
        }
        resultStatisticsMap.put(rank, INITIAL_VALUE_ONE);
    }

    public int size() {
        return this.lottoTickets.size();
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(this.lottoTickets);
    }
}
