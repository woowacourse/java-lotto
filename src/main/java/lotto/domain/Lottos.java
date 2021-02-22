package lotto.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lottos {
    
    private final List<Lotto> lottos;
    
    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }
    
    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
    
    public LottoStatisticResult retrieveResults(WinningLotto winningLotto) {
        Map<Rank, Long> rankCount = this.lottos.stream()
                                               .map(lotto -> Rank.searchRank(winningLotto, lotto))
                                               .collect(groupingBy(
                                                       Function.identity(),
                                                       () -> new EnumMap<>(Rank.class),
                                                       counting()));
        
        return new LottoStatisticResult(rankCount);
    }
}
