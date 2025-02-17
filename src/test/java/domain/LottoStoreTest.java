package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    @Test
    void 돈을_이용해_로또를_살_수_있다() {
        //given
        Money money = new Money(3000);
        LottoPickStrategy fixNumberStrategy = (int size) -> List.of(1, 2, 3, 4, 5, 6);
        LottoStore lottoStore = new LottoStore(fixNumberStrategy);

        //when
        Lottos lottos = lottoStore.buy(money);

        //then
        assertThat(lottos).extracting("lottos")
                .isEqualTo(List.of(
                        Lotto.createRandomLotto((int size) -> List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createRandomLotto((int size) -> List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createRandomLotto((int size) -> List.of(1, 2, 3, 4, 5, 6))
                ));
    }
}
