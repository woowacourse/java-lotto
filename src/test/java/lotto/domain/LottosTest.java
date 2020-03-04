package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("개수를 넣었을 때 개수만큼 로또를 생성해줌")
    @Test
    void makeLottos() {
        Lotto firstLotto = Lotto.of("1, 2, 3, 4, 5, 6");
        Lotto secondLotto = Lotto.of("1, 2, 3, 4, 5, 6");

        Lottos lottos = new Lottos(Arrays.asList(firstLotto, secondLotto), new LottoCount(2));
        assertThat(lottos.isSameCount(2)).isTrue();
    }

}
