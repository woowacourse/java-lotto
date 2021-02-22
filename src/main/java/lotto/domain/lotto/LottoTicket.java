package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class LottoTicket {

    private static final int LOTTO_PRICE = 1000;
    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(List<LottoNumbers> lottoTicket) {
        this.lottoTicket = new ArrayList<>(lottoTicket);
    }

    public WinningStatistics getWinningStatistics(WinningNumbers winningNumbers) {
        Ranks ranks = checkLottoRanks(winningNumbers);
        Yield yield = new Yield(ranks.getTotalWinnings() / getTotalLottoPrice());

        return new WinningStatistics(ranks, yield);
    }

    private Ranks checkLottoRanks(WinningNumbers winningNumbers) {
        return new Ranks(lottoTicket.stream()
            .map(lottoNumbers -> winningNumbers.checkRank(lottoNumbers))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                LottoTicket::fillUnrankedCount)));
    }

    private static Map<Rank, Long> fillUnrankedCount(Map<Rank, Long> statistics) {
        Arrays.stream(Rank.values())
            .filter(rank -> !statistics.containsKey(rank))
            .forEach(rank -> statistics.put(rank, 0L));

        return statistics;
    }

    public List<LottoNumbers> unwrap() {
        return new ArrayList<>(lottoTicket);
    }

    public static int getLottoPrice() {
        return LOTTO_PRICE;
    }

    public int count() {
        return lottoTicket.size();
    }

    public int getTotalLottoPrice() {
        return lottoTicket.size() * LOTTO_PRICE;
    }
}