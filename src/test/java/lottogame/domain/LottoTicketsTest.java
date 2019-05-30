package lottogame.domain;

import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    void 금액에_맞게_생성되는지_테스트() {
        Money money = new Money(10000);
        LottoTickets lottoTickets = new LottoTickets(10,money);
        System.out.println(lottoTickets);
    }
}
