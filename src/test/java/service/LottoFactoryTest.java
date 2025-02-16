package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import model.Lotto;
import model.LottoConstant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoFactoryTest {

    private static LottoFactory lottoFactory;
    private static int purchase;

    @BeforeAll
    static void beforeAll() {
        purchase = 10000;
        lottoFactory = LottoFactory.of(purchase);
    }

    @ParameterizedTest(name = "구매 티켓 갯수는 구매 금액에 나누기 {0}이여야 한다.")
    @ValueSource(ints = {LottoConstant.TICKET_PRICE_UNIT})
    void validTicketNumber(final int lottoPurchaseUnit) {
        int expected = purchase / lottoPurchaseUnit;
        assertThat(lottoFactory.getTicketNumber()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "발행된 티켓 내 숫자들은 모두 {0}부터 {1} 사이여야 한다.")
    @MethodSource("provideLottoRange")
    void validTicketRange(final int lottoMinRange, final int lottoMaxRange) {
        for (Lotto issuedTicket : lottoFactory.getIssuedTickets()) {
            issuedTicket.getNumbers()
                    .forEach(number -> assertThat(number).isBetween(lottoMinRange, lottoMaxRange));
        }
    }

    @ParameterizedTest(name = "발행된 티켓 내 숫자 갯수는 {0}개여야 한다.")
    @ValueSource(ints = {LottoConstant.SIZE})
    void validTicketSize(final int expectedCount) {
        for (Lotto issuedTicket : lottoFactory.getIssuedTickets()) {
            assertThat(issuedTicket.getNumbers().size()).isEqualTo(expectedCount);
        }
    }

    static Stream<Arguments> provideLottoRange() {
        return Stream.of(Arguments.of(LottoConstant.MIN_NUMBER, LottoConstant.MAX_NUMBER));
    }
}
