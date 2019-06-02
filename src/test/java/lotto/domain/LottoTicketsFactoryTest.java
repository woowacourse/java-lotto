package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsFactoryTest {
    @Test
    void create() {
        String[] lottoTickets = "1,2,3,4,5,6\n10,14,21,24,34,45\n7,9,12,19,23,38".split("\n");
        assertThat(LottoTicketsFactory.create(lottoTickets))
                .isEqualTo(
                        Arrays.asList(
                                new LottoTicket(Arrays.asList(
                                        LottoNumber.getNumber(1), LottoNumber.getNumber(2), LottoNumber.getNumber(3),
                                        LottoNumber.getNumber(4), LottoNumber.getNumber(5), LottoNumber.getNumber(6))),
                                new LottoTicket(Arrays.asList(
                                        LottoNumber.getNumber(10), LottoNumber.getNumber(14), LottoNumber.getNumber(21),
                                        LottoNumber.getNumber(24), LottoNumber.getNumber(34), LottoNumber.getNumber(45))),
                                new LottoTicket(Arrays.asList(
                                        LottoNumber.getNumber(7), LottoNumber.getNumber(9), LottoNumber.getNumber(12),
                                        LottoNumber.getNumber(19), LottoNumber.getNumber(23), LottoNumber.getNumber(38)))));
    }
}
