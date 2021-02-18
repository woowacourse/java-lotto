package domain.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BonusBallTest {

    @DisplayName("BonusBallr정상 생성 테스트")
    @Test
    void BonusBall_생성된다() {
        assertThatCode(() -> BonusBall.of(4))
                .doesNotThrowAnyException();
    }

    @DisplayName("BonusBall isSameNumber 테스트")
    @Test
    void BonusBall_isSameNumber_테스트() {
        assertThat(BonusBall.of(5)
                .isSameNumber(new LottoBall(5))).isTrue();
    }
}