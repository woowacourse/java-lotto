package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    private static Stream<Arguments> wrongSizeBallsProvider() {
        return Stream.of(
                Arguments.of("6개 보다 적은 경우", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of("6개 보다 많은 경우", Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @DisplayName("로또 티켓 생성하기")
    @Test
    void test1() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 5, 6));

        LottoTicket expectedTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lottoTicket).isEqualTo(expectedTicket);
    }

    @DisplayName("6개가 아닌 로또 번호를 입력받은 티켓은 Exception 발생")
    @ParameterizedTest(name = "[{0}] {1}")
    @MethodSource("wrongSizeBallsProvider")
    void test2(String header, List<Integer> lottoBalls) {

        int wrongSize = lottoBalls.size();

        assertThatThrownBy(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 갯수가 %d개로 올바르지 않습니다.", wrongSize);
    }
}
