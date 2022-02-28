package model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.lottonumbergenerator.Generator;
import model.lottotickets.LottoTicket;
import model.winning.Rank;
import model.winning.Statistics;

public class Lottos {
    private final List<LottoTicket> lottoTickets;

    public Lottos(final int purchaseCount, final Generator generator) {
        lottoTickets = IntStream
                .rangeClosed(1, purchaseCount)
                .mapToObj(index -> new LottoTicket(generator))
                .collect(Collectors.toList());
    }

    public Statistics winningResult(final List<Integer> winningNumbers, final Integer bonusNumber) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank key = lottoTicket.selectRank(winningNumbers, bonusNumber);
            result.put(key, result.get(key) + 1);
        }
        return new Statistics(result);
    }

    public List<List<Integer>> tickets() {
        return lottoTickets.stream()
                .map(LottoTicket::lottoNumbers)
                .collect(Collectors.toList());
    }
}
