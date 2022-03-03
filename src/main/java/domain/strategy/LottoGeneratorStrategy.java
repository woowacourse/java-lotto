package domain.strategy;

import java.util.List;

import domain.Lotto;
import domain.LottoPurchaseInfo;

public interface LottoGeneratorStrategy {

    List<Lotto> generate(LottoPurchaseInfo lottoPurchaseInfo);
}
