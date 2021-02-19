package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lottos {
    
    private final List<Lotto> lottos;
    
    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }
    
    public static Lottos makeLottos(int payCount) {
        List<Lotto> lottos = Stream.generate(() -> Lotto.fromGenerator(new RandomNumberGenerator()))
                                   .limit(payCount)
                                   .collect(Collectors.toList());
        
        return new Lottos(lottos);
    }
    
    public List<List<Integer>> toInts() {
        return lottos.stream()
                     .map(Lotto::toInts)
                     .collect(Collectors.toList());
    }
    
    public LottoStatisticResult retrieveResults(WinningLotto winningLotto) {
        Map<Rank, Long> rankCount = this.lottos.stream()
                                               .map(lotto -> Rank.searchRank(winningLotto, lotto))
                                               .collect(groupingBy(Function.identity(), counting()));
        
        return new LottoStatisticResult(rankCount);
    }
}
