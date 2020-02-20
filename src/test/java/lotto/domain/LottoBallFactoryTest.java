package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoBallFactoryTest {
    @Test
    @DisplayName("원하는 로또값 생성 확인 테스트")
    void check_want_lotto_ballTest() {
        int lottoBallNumber = 1;
        Assertions.assertThat(LottoBallFactory.findByLottoBall(1).getLottoNumber()).isEqualTo(1);
    }
}
