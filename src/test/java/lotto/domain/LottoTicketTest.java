package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성하기")
    @Test
    void test1() {
        Set<LottoBall> lottoBalls = new HashSet<>(Arrays.asList(new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)));

        LottoTicket lottoTicket = new LottoTicket(lottoBalls);

        LottoTicket expectedTicket = new LottoTicket(new HashSet<>(Arrays.asList(new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6))));
        assertThat(lottoTicket).isEqualTo(expectedTicket);
    }

    @DisplayName("잘못된 갯수의 로또 번호를 입력받은 티켓은 Exception 발생")
    @Test
    void test2() {
        Set<LottoBall> lottoBalls = new HashSet<>(Arrays.asList(new LottoBall(1),
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)));
        int wrongSize = lottoBalls.size();

        assertThatThrownBy(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 갯수가 %d개로 올바르지 않습니다.", wrongSize);
    }
}
