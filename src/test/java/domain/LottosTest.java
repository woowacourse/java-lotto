package domain;

import static domain.lotto.Lotto.LOTTO_PRICE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import domain.lotto.Lottos;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {2000, 3000, 4000})
    void 로또를_발행한다(int money) {
        List<Integer> dummyLottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lottos lottos = Lottos.issue(
                money,
                (minNumber, maxNumber, maxSize) -> dummyLottoNumbers
        );

        assertThat(lottos.getLottos().getFirst().getBallNumbers())
                .isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lottos.getLottos().size()).isEqualTo(money / LOTTO_PRICE);
    }

}
