package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoFactoryTest {

    @DisplayName("로또 볼 비교")
    @ParameterizedTest
    @ValueSource(ints = {1, 12, 23, 4, 5, 6})
    void makeLotto(int ball) {
        LottoFactory lottoFactory = () -> {
            Set<Ball> balls = new HashSet<>(Arrays
                .asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6)));
            return new Lotto(balls);
        };
        assertThat(lottoFactory.create().contains(Ball.of(ball))).isTrue();
    }

}
