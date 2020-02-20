package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 볼이 비어있을 경우 테스트")
    void throw_empty_lotto_ball_test() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        Assertions.assertThatThrownBy(()->new LottoTicket(lottoTicket))
                .isInstanceOf(LottoTicketEmptyException.class);
    }
}
