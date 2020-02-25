package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("개수를 넣었을 때 개수만큼 로또를 생성해줌")
    @Test
    void makeLottos() {
        Set<Ball> balls = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto firstLotto = new Lotto(balls);
        Lotto secondLotto = new Lotto(balls);

        Lottos lottos = new Lottos(Arrays.asList(firstLotto, secondLotto), new LottoCount(2));
        assertThat(lottos.isSameCount(2)).isTrue();
    }

}
