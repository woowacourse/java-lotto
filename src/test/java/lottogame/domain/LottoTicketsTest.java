package lottogame.domain;

import lottogame.utils.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketsTest {
    @Test
    void 금액이_잘못되었으면_예외를_발생하는지_테스트() {
        assertThrows(InvalidLottoPriceException.class,()-> new LottoTickets(999));
    }

    @Test
    void 금액에_맞게_생성되는지_테스트() {
        LottoTickets lottoTickets = new LottoTickets(10000);
        System.out.println(lottoTickets);
    }
}
