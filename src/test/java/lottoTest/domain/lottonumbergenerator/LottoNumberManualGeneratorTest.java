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
        List<LottoNumber> lottoNumbers = lottoNumberGenerator.getLottoNumbersBy(1).get(0);
        assertThat(lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
        ).isEqualTo(inputNumbers);
    }

    @Test
    void 생성할_로또_개수와_로또_숫자_개수가_다른_경우_테스트() {
        List<List<Integer>> numbers = new ArrayList<>();
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberManualGenerator(numbers);
        assertThatThrownBy(() -> lottoNumberGenerator.getLottoNumbersBy(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("생성할 로또 개수와 로또 숫자 개수가 다릅니다.");
    }

    @Test
    void 로또번호를_6개_오름차순으로_정렬하여_생성하는_기능_테스트() {
        List<List<Integer>> numbers = new ArrayList<>();
        List<Integer> inputNumbers = List.of(6, 4, 2, 1, 5, 3);
        numbers.add(inputNumbers);
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberManualGenerator(numbers);
        List<LottoNumber> lottoNumbers = lottoNumberGenerator.getLottoNumbersBy(1).get(0);
        assertThat(lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
        ).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}