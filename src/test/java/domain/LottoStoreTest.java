package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.numbergenerator.NumberGenerator;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    static class FixNumberGenerator implements NumberGenerator {
        int index = 0;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        @Override
        public int generate() {
            int number = numbers.get(index++);
            index %= numbers.size();
            return number;
        }
    }

    @Test
    void 돈을_이용해_로또를_살_수_있다() {
        //given
        Money money = new Money(3000);
        LottoStore lottoStore = new LottoStore();

        //when
        Lottos lottos = lottoStore.buy(money, new FixNumberGenerator());

        //then
        assertThat(lottos).extracting("lottos")
                .isEqualTo(List.of(
                        new Lotto(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6))),
                        new Lotto(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6))),
                        new Lotto(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6)))
                ));
    }

    @Test
    void 구입금액이_1000원_단위가_아니라면_예외가_발생한다() {
        //given
        Money money = new Money(1500);
        LottoStore lottoStore = new LottoStore();

        //when
        Assertions.assertThatThrownBy(() -> lottoStore.buy(money, new FixNumberGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또를 사기 위해서는 1000원 단위여야 합니다.");

    }
}
