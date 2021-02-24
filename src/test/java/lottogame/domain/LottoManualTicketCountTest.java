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
    @DisplayName("5000원을 가지고 3개의 로또 티켓 카운트를 만들면 3회 반복된다.")
    void getValueTest() {
        LottoManualTicketCount lottoManualTicketCount =
                new LottoManualTicketCount("3", new Money("5000"));

        int count = 0;
        while (lottoManualTicketCount.isRemain()) {
            count++;
            lottoManualTicketCount.reduce();
        }
        assertThat(count).isEqualTo(3);
    }
}
