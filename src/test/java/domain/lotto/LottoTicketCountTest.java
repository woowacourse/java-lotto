package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketCountTest {

    @DisplayName("LottoCount 정상 생성된다.")
    @Test
    void LottoCount_생성_테스트() {
        assertThatCode(() -> TicketCount.of(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoCount가 0일시 에러 출력한다.")
    @Test
    void LottoCount_생성_0일시_테스트() {
        assertThatThrownBy(() -> TicketCount.of(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}