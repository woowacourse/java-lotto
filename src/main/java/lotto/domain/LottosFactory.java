package lotto.domain;

import lotto.domain.generator.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottosFactory {
    
    private LottosFactory() {}
    
    public static Lottos makeLottos(PurchaseCount purchaseCount) {
        List<Lotto> lottos = Stream.generate(() -> Lotto.fromGenerator(new RandomNumberGenerator()))
                                   .limit(purchaseCount.getAutomaticPurchaseCount())
                                   .collect(Collectors.toList());
        
        return new Lottos(lottos);
    }
}
