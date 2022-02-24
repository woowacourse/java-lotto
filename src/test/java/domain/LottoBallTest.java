package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoBallTest {
    @Test
    void 로또_번호_일치_여부_검사() {
        LottoBall lottoBall = LottoBall.from(1);
        LottoBall lottoBall2 = LottoBall.from(1);

        assertThat(lottoBall).isEqualTo(lottoBall2);
    }
}