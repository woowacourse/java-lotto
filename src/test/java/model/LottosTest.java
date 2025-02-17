package model;

import model.lotto.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {

    @Test
    void 양에_따라_로또가_생성된다() {
        // given
        int amount = 3;

        // when
        Lottos lottos = new Lottos(amount);
        List<Lotto> result = lottos.getLottos();

        // then
        assertThat(result.size()).isEqualTo(amount);
    }

    @Test
    void 불변_로또_객체가_변경시_예외가_발생한다() {
        // given
        Lottos lottos = new Lottos(5);

        // when
        List<Lotto> immutableLottos = lottos.getLottos();

        // then
        assertThatThrownBy(() -> immutableLottos.remove(1))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
