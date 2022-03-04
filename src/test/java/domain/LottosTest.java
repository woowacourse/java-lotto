package domain;

import domain.strategy.ManualLottoNumberStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    @DisplayName("두 개의 Lottos 객체 합치기")
    void concatenate() {
        Lottos firstLottos = new Lottos(new ManualLottoNumberStrategy(List.of(List.of(1, 2, 3, 4, 5, 6))),
                PurchaseCount.from(new Money(1000), 1));
        Lottos secondLottos = new Lottos(new ManualLottoNumberStrategy(List.of(List.of(7, 8, 9, 10, 11, 12))),
                PurchaseCount.from(new Money(1000), 1));
        firstLottos.concatenate(secondLottos);

        List<Integer> numbers = firstLottos.concatenate(secondLottos).getLottos().stream()
                .map(LottoNumbers::getLottoNumbers)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }
}