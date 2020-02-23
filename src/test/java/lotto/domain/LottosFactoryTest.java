package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosFactoryTest {

    @DisplayName("수동 로또 생성 테스트")
    @Test
    void makeLottos() {
        LottoFactory lottoFactory = () -> {
            Set<Ball> balls = new HashSet<>(Arrays
                .asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6)));
            return new Lotto(balls);
        };
        LottosFactory lottosFactory = new LottosFactory(lottoFactory);

        Lottos lottos = lottosFactory.createLottosByCount(new LottoCount(5));
        assertThat(lottos.isSameCount(5)).isTrue();
    }
}
