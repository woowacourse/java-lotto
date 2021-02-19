package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    public List<Lotto> getLottos() {
        return lottos;
    }
    
    public List<List<Integer>> toInts() {
        return lottos.stream()
                     .map(Lotto::toInts)
                     .collect(Collectors.toList());
    }
}
