package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class TicketCountTest {

    @DisplayName("ticketCount 정상 생성 테스트.")
    @Test
    void ticketCountGenerateTest() {
        assertThatCode(() -> new TicketCount(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("ticketCount의 수가 0미만 이면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void ticketCountGenerateErrorTest(int value) {
        assertThatThrownBy(() -> new TicketCount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매가능한(1 이상, 현재 구매할 수 있는 티켓수보다 같거나 작은 수) 수동 로또 개가 들어오면 TicketCount를 감소한다")
    @Test
    void lottoCountReduceTest() {
        //given
        int ticketNumber = 11;
        int manualTicketCount = 3;

        //when
        TicketCount ticketCount = new TicketCount(ticketNumber);

        //then
        assertThat(ticketCount.reduceTicketCount(manualTicketCount)).isEqualTo(new TicketCount(8));
    }

    @DisplayName("구매가능한(1 이상, 현재 구매할 수 있는 티켓수보다 같거나 작은 수) 수동 로또 갯수가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 12})
    void manualTicketCountErrorTest(int manualTicketCount) {
        //given
        int ticketNumber = 11;

        //when
        TicketCount ticketCount = new TicketCount(ticketNumber);

        //then
        assertThatThrownBy(() -> ticketCount.reduceTicketCount(manualTicketCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}