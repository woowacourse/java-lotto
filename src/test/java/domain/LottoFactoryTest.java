package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {
    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        final LottoNumbers winNumbers = new LottoNumbers("1, 2, 3, 4, 5, 6");
        final LottoNumber bonusNumber = new LottoNumber("7");
        final Money money = new Money("2500");

        lottoFactory = new LottoFactory(money, winNumbers, bonusNumber);
    }

    @DisplayName("구입 금액을 로또 발급 갯수로 반환을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1100,1", "3001,3"}, delimiter = ',')
    void money_to_counts(String inputMoney, int counts) {
        final Money money = new Money(inputMoney);

        assertThat(lottoFactory.calculateCount(money)).isEqualTo(new Count(counts));
    }

    @DisplayName("자동 로또 발급시 번호 갯수가 6개인지 확인한다.")
    @Test
    void autoLottoNumbers_size_correct() {
        final LottoNumbers lottoNumbers = lottoFactory.generateAutoLottoNumbers();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        final Count count = lottoFactory.calculateCount(new Money("2500"));
        lottoFactory.issueLotto(count);
        final List<LottoNumbers> lottoTickets = lottoFactory.getLottoTickets();

        assertThat(lottoTickets.size()).isEqualTo(2);
    }

    @Test
    void create_bonus_number() {
        lottoFactory.generateAutoLottoNumbers();
        lottoFactory.getLottoTickets();
        lottoFactory.compare();
    }
}
