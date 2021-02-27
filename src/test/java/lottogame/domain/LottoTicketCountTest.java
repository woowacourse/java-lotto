package lottogame.domain;

import lottogame.view.LottoTicketCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTicketCountTest {
    @Test
    @DisplayName("tryCount 입력이 양의 정수가 아니면 예외를 발생시킨다.")
    void positiveNumberTest() {
        assertThatThrownBy(() -> new LottoTicketCount("a", new Money("1000")))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new LottoTicketCount("-1", new Money("1000")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈의 액수보다, 더 많은 티켓을 수동 구매하려 하면 예외를 발생시킨다.")
    void validCountTest() {
        assertThatThrownBy(() -> new LottoTicketCount("3", new Money("2999")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("5000원을 가지고 3개의 로또 티켓 카운트를 만들면 3회 반복된다.")
    void getValueTest() {
        LottoTicketCount lottoTicketCount =
                new LottoTicketCount("3", new Money("5000"));

        int count = 0;
        while (lottoTicketCount.isRemain()) {
            count++;
            lottoTicketCount.reduce();
        }
        assertThat(count).isEqualTo(3);
    }
}
