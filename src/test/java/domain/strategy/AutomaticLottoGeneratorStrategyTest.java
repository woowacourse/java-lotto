package domain.strategy;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoPurchaseCount;
import domain.LottoPurchaseInfo;

class AutomaticLottoGeneratorStrategyTest {

    @Test
    @DisplayName("자동생성 방식의 로또를 생성하는 기능")
    void createRandomLotto() {
        LottoGeneratorStrategy lottoGeneratorStrategy = new AutomaticLottoGeneratorStrategy();
        LottoPurchaseInfo lottoPurchaseInfo = new LottoPurchaseInfo(
            List.of(List.of(1, 2, 3, 4, 5, 6)),
            new LottoPurchaseCount(1, 1));

        List<Lotto> lottos = lottoGeneratorStrategy.generate(lottoPurchaseInfo);

        assertThat(lottos).isNotNull();
    }
}
