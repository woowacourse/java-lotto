package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성하기")
    @Test
    void test1() {
        List<LottoBall> lottoBalls = new ArrayList<>(Arrays.asList(new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)));

        LottoTicket lottoTicket = new LottoTicket(lottoBalls);

        LottoTicket expectedTicket = new LottoTicket(Arrays.asList(new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)));
        assertThat(lottoTicket).isEqualTo(expectedTicket);
    }
}
