package domain.strategy;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoPurchaseCount;
import domain.LottoPurchaseInfo;

class ManualLottoGeneratorStrategyTest {

    @Test
    @DisplayName("수동 로또들을 생성하는 기능")
    void createManualLottos() {
        LottoGeneratorStrategy lottoGeneratorStrategy = new ManualLottoGeneratorStrategy();
        List<List<Integer>> lotto = List.of(List.of(1, 2, 3, 4, 5, 6));
        LottoPurchaseInfo lottoPurchaseInfo = new LottoPurchaseInfo(
            lotto, new LottoPurchaseCount(1, 0));

        assertThat(lottoGeneratorStrategy.generate(lottoPurchaseInfo)).contains(
            new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList()))
        );
    }
}