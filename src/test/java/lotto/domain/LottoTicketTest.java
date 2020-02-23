package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.NumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 티켓에 로또 볼이 비어있을 경우 테스트")
    void throw_empty_lotto_ball_test() {
        Set<LottoBall> lottoTicket = new HashSet<>();

        assertThatThrownBy(() -> new LottoTicket(lottoTicket))
                .isInstanceOf(LottoTicketEmptyException.class);
    }

    @Test
    @DisplayName("로또 티켓이 제대로 생성되었을 경우 테스트")
    void not_empty_lotto_ball_test() {
        Set<LottoBall> lottoTicket = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toSet());

        assertThatCode(() -> new LottoTicket(lottoTicket))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("지정된 숫자 이상의 로또 볼이 생성되었을 경우 예외처리 테스트")
    void out_of_range_for_seven_test() {
        Set<LottoBall> lottoTicket = IntStream.rangeClosed(1, 7)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toSet());

        assertThatThrownBy(() -> new LottoTicket(lottoTicket)).isInstanceOf(NumberOutOfRangeException.class)
                .hasMessage(LottoTicket.LOTTO_TICKET_SIZE + "개 이상의 숫자가 들어갔습니다.");
    }
}
