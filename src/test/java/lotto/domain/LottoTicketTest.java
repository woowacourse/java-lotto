package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성하기")
    @Test
    void test1() {
        List<LottoBall> lottoBalls = new ArrayList<>(Arrays.asList(new LottoBall()));

        LottoTicket lottoTicket = new LottoTicket(lottoBalls);


    }
}