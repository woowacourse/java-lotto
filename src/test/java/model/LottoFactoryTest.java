package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.random.RandomUtil;
import util.random.TestRandomUtil;

class LottoFactoryTest {

    private LottoFactory lottoFactory;

    @BeforeEach
    void beforeEach() {
        LottoPurchase lottoPurchase = LottoPurchase.of(Integer.toString(10000));
        List<Integer> fixedResults = List.of(1, 5, 10, 15, 20, 25);
        RandomUtil randomUtil = new TestRandomUtil(fixedResults);
        lottoFactory = LottoFactory.of(lottoPurchase, randomUtil);
    }

    @Test
    @DisplayName("구매 티켓 갯수는 구매 금액에 나누기 1000이여야 한다.")
    void validTicketNumber() {
        String expected = "10";
        assertThat(lottoFactory.lottoCountToString()).isEqualTo(expected);
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자들은 모두 1 부터 45 사이여야 한다.")
    void validTicketRange() {
        assertThat(lottoFactory)
                .extracting("issuedLottoTickets", list(Lotto.class))
                .flatExtracting("numbers")
                .allSatisfy(number -> assertThat((Integer) number).isBetween(1, 45));
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자 갯수는 6개여야 한다.")
    void validTicketSize() {
        assertThat(lottoFactory)
                .extracting("issuedLottoTickets", list(Lotto.class))
                .extracting("numbers")
                .allSatisfy(numbers -> assertThat((List<Integer>) numbers).hasSize(6));
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자를 검증한다.")
    void validTicketNumbers() {
        assertThat(lottoFactory)
                .extracting("issuedLottoTickets", list(Lotto.class))
                .extracting("numbers")
                .allSatisfy(numbers -> assertThat(numbers).isEqualTo(List.of(1, 5, 10, 15, 20, 25)));
    }

    @Test
    @DisplayName("수익률은 당첨합계 / 원금 이어야 한다.")
    void validBenefit() {
        EnumMap<Prize, Integer> prizeMap = Prize.initialize();
        prizeMap.put(Prize.FIFTH_PLACE, 3);

        double benefit = lottoFactory.getWinningAmount(prizeMap);
        double expected = 1.5;
        assertThat(benefit).isEqualTo(expected);
    }
}
