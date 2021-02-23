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

    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(List<LottoNumbers> lottoTicket) {
        this.lottoTicket = new ArrayList<>(lottoTicket);
    }

    public Ranks calculateRanks(WinningNumbers winningNumbers) {
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

    public int getCountByType(LottoNumbersType type) {
        return (int) lottoTicket.stream()
            .filter(lottoNumbers -> lottoNumbers.getType() == type)
            .count();
    }

    public int count() {
        return lottoTicket.size();
    }
}