package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Lottos;
import dto.LottoDto;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.RandomGenerator;

class LottoGenerateServiceTest {

    private final LottoGenerateService service = new LottoGenerateService();

    @DisplayName("구매 금액에 알맞는 개수의 로또를 생성한다.")
    @ParameterizedTest(name = "{0}원 구매 시 {1}개의 로또 생성")
    @CsvSource(value = {"1000,1", "2000,2", "10000,10", "100000,100"})
    void calculate_lotto_count_by_purchasing_amount(int purchasingAmount, int lottoCount) {
        Lottos lottos = service.generateLottos(purchasingAmount, () -> List.of(1, 2, 3, 4, 5, 6));
        List<LottoDto> lottoDtos = lottos.toDto().getLottoDtos();

        assertThat(lottoDtos.size()).isEqualTo(lottoCount);
    }

    @DisplayName("번호 생성 전략에 알맞는 번호로 된 로또를 생성한다.")
    @Test
    void generate_valid_numbers() {
        Lottos lottos = service.generateLottos(2000, new SequencedTestStrategy());
        List<LottoDto> lottoDtos = lottos.toDto().getLottoDtos();

        assertAll(
            () -> assertThat(lottoDtos.getFirst().getNumbers())
                .containsExactlyElementsOf(List.of(1, 2, 3, 4, 5, 6)),
            () -> assertThat(lottoDtos.getLast().getNumbers())
                .containsExactlyElementsOf(List.of(7, 8, 9, 10, 11, 12))
        );
    }

    static class SequencedTestStrategy implements NumbersStrategy {

        private int next = 1;

        @Override
        public List<Integer> get() {
            List<Integer> generated = IntStream.range(next, next + 6)
                .boxed()
                .toList();
            next += 6;
            return generated;
        }
    }
}
