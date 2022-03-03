package lottoTest.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberManualStrategy;
import lotto.domain.LottoNumberStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberManualGeneratorTest {

    @Test
    void 로또_번호를_수동으로_생성하는_기능() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        input.add(Arrays.asList(7, 8, 9, 10, 11, 12));

        LottoNumberStrategy generator = LottoNumberManualStrategy.generateManualLottoNumbers(input);

        Assertions.assertThat(generator.getLottoNumbers().get(0))
                .containsExactly(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));

        Assertions.assertThat(generator.getLottoNumbers().get(1))
                .containsExactly(LottoNumber.valueOf(7), LottoNumber.valueOf(8), LottoNumber.valueOf(9),
                        LottoNumber.valueOf(10), LottoNumber.valueOf(11), LottoNumber.valueOf(12));
    }
}