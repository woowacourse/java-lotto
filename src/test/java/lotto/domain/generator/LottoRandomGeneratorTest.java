package lotto.domain.generator;

import lotto.domain.LottoNumbers;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRandomGeneratorTest {
    final int numberOfGenerating = 5;
    final LottoGenerator lottoRandomGenerator = new LottoRandomGenerator();
    final List<LottoNumbers> generated =
            lottoRandomGenerator.generateLottoNumbersGroup(numberOfGenerating, Collections.EMPTY_LIST);

    @Test
    @DisplayName("올바른 값으로 로또 번호들이 예외 없이 자동으로 만들어지는지 검증한다.")
    void checkLottoNumberRangeByGenerate() {
        //given
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        final int expected = 30;
        //when
        generated.forEach(lottoNumbers -> lottoNumberGroup.addAll(lottoNumbers.getValues()));
        //then
        assertThat(lottoNumberGroup.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자 값을 입력받아, 입력받은 값만큼의 로또 개수를 서로 중복없이 생성해 반환한다.")
    void generate() {
        //when
        final int actualDistinctCount = (int) generated.stream()
                .distinct()
                .count();
        //then
        assertThat(actualDistinctCount).isEqualTo(numberOfGenerating);
    }
}
