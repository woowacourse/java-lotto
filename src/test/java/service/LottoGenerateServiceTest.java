package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoGenerateServiceTest {

    private final LottoGenerateService service = new LottoGenerateService();

    @DisplayName("구매 금액에 알맞는 개수의 로또를 생성한다.")
    @ParameterizedTest(name = "{0}원 구매 시 {1}개의 로또 생성")
    @CsvSource(value = {"1000,1", "2000,2", "10000,10", "100000,100"})
    void calculate_lotto_count_by_purchasing_amount(int purchasingAmount, int lottoCount) {
        Lottos lottos = service.generateLottos(purchasingAmount, new TestLottoNumbersGenerator());
        List<Lotto> allLottos = lottos.getLottos();

        assertThat(allLottos.size()).isEqualTo(lottoCount);
    }

    static class TestLottoNumbersGenerator implements LottoNumbersGenerator {

        @Override
        public List<LottoNumber> generate() {
            List<LottoNumber> generated = IntStream.range(1, 7)
                .boxed()
                .map(LottoNumber::new)
                .toList();
            return generated;
        }
    }
}
