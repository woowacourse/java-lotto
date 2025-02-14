package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.numberstrategy.NumberPickStrategy;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
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

    @Test
    void 구입금액이_1000원_단위가_아니라면_예외가_발생한다() {
        //given
        Money money = new Money(1500);
        LottoMachine lottoMachine = new LottoMachine((maxNumber, size) -> List.of(1, 2, 3, 4, 5, 6));
        LottoStore lottoStore = new LottoStore(lottoMachine);

        //when
        Assertions.assertThatThrownBy(() -> lottoStore.buy(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또를 사기 위해서는 1000원 단위여야 합니다.");

    }
}