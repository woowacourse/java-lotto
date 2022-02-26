package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {
    private final LottoFactory lottoFactory = LottoFactory.getInstance();

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("천원 당 한 장의 로또를 구매할 수 있다.")
    void purchaseLotto(int money, int ticketCount) {
        // given

        // when
        List<Lotto> lotteries = lottoFactory.generateLotteries(money);

        // then
        assertThat(lotteries.size()).isEqualTo(ticketCount);
    }
}
