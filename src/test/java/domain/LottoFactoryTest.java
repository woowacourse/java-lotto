package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {
    @DisplayName("구입 금액을 로또 발급 갯수로 반환을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1100,1", "3001,3"}, delimiter = ',')
    void money_to_counts(String inputMoney, int counts) {
        LottoFactory lottoFactory = new LottoFactory();
        final Money money = new Money(inputMoney);

        assertThat(lottoFactory.calculateCount(money)).isEqualTo(counts);
    }
}
