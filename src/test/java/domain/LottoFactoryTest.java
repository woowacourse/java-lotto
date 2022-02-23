package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {
    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        lottoFactory = new LottoFactory();
    }

    @DisplayName("구입 금액을 로또 발급 갯수로 반환을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1100,1", "3001,3"}, delimiter = ',')
    void money_to_counts(String inputMoney, int counts) {
        final Money money = new Money(inputMoney);

        assertThat(lottoFactory.calculateCount(money)).isEqualTo(counts);
    }

    @DisplayName("개별 로또 번호 생성을 확인한다.")
    @Test
    void generate_autoLottoNumber_correct() {
        final LottoNumber lottoNumber = lottoFactory.generateAutoLottoNumber(4);

        assertThat(lottoNumber).isEqualTo(new LottoNumber(4));
    }

    @DisplayName("자동 로또 발급시 번호 갯수가 6개인지 확인한다.")
    @Test
    void autoLottoNumbers_size_correct() {
        final LottoNumbers lottoNumbers = lottoFactory.generateAutoLottoNumbers();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}
