package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottosFactory {
    
    private LottosFactory() {}
    
    public static Lottos makeLottos(PaymentAmount paymentAmount) {
        List<Lotto> lottos = Stream.generate(() -> Lotto.fromGenerator(new RandomNumberGenerator()))
                                   .limit(paymentAmount.getPurchaseCount())
                                   .collect(Collectors.toList());
        
        return new Lottos(lottos);
    }
}
