package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.numberstrategy.NumberPickStrategy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    @Test
    void 돈을_이용해_로또를_살_수_있다() {
        //given
        Money money = new Money(3000);
        NumberPickStrategy fixNumberStrategy = (int maxNumber, int size) -> List.of(1, 2, 3, 4, 5, 6);
        LottoMachine lottoMachine = new LottoMachine(fixNumberStrategy);
        LottoStore lottoStore = new LottoStore(lottoMachine);

        //when
        Lottos lottos = lottoStore.buy(money);

        //then
        assertThat(lottos).extracting("lottos")
                .isEqualTo(List.of(
                        new Lotto(Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                                new Number(6))),
                        new Lotto(Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                                new Number(6))),
                        new Lotto(Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                                new Number(6)))
                ));
    }
}
