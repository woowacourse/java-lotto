package lottogame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoManualTicketCountTest {
    @Test
    @DisplayName("tryCount 입력이 양의 정수가 아니면 예외를 발생시킨다.")
    void positiveNumberTest() {
        assertThatThrownBy(() -> new LottoManualTicketCount("a", new Money("1000")))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new LottoManualTicketCount("-1", new Money("1000")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈의 액수보다, 더 많은 티켓을 수동 구매하려 하면 예외를 발생시킨다.")
    void validCountTest() {
        assertThatThrownBy(() -> new LottoManualTicketCount("3", new Money("2999")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("5000원을 가지고 3개의 티켓을 구매 시도하면, 3개를 count를 가지고 있다.")
    void getValueTest() {
        LottoManualTicketCount lottoManualTicketCount =
                new LottoManualTicketCount("3", new Money("5000"));
        assertThat(lottoManualTicketCount.value()).isEqualTo(3);
    }
}
