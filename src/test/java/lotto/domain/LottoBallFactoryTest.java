package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoBallFactoryTest {
    @Test
    void 원하는_로또값_생성_확인() {
        int lottoBallNumber = 1;
        Assertions.assertThat(LottoBallFactory.findByLottoBall(1).getLottoNumber()).isEqualTo(1);
    }
}
