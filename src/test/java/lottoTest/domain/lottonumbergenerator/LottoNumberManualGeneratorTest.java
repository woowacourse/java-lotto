package lottoTest.domain.lottonumbergenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.lottonumbergenerator.LottoNumberGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberManualGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberManualGeneratorTest {

    @Test
    void 수동_로또를_생성하는_기능_테스트() {
        List<List<Integer>> numbers = new ArrayList<>();
        List<Integer> inputNumbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.add(inputNumbers);
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberManualGenerator(numbers);
        List<LottoNumber> lottoNumbers = lottoNumberGenerator.getLottoNumbers(6);
        assertThat(lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
        ).isEqualTo(inputNumbers);
    }

    @Test
    void 더이상_생성할_수동_로또가_없는_경우_테스트() {
        List<List<Integer>> numbers = new ArrayList<>();
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberManualGenerator(numbers);
        assertThatThrownBy(() -> lottoNumberGenerator.getLottoNumbers(6))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("생성할 수동 로또 번호가 없습니다.");
    }
}