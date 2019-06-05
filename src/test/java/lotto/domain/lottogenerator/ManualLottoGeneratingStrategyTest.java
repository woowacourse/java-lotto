package lotto.domain.lottogenerator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ManualLottoGeneratingStrategyTest {
    @Test
    void 생성자_오류_확인_입력받은_로또_숫자가_null_인_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new ManualLottoGeneratingStrategy(null));
    }

    @Test
    void 생성자_오류_확인_입력받은_로또_숫자가_비어있는_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new ManualLottoGeneratingStrategy(Collections.emptyList()));
    }

    @Test
    void 수동으로_로또번호_생성되는지_확인() {
        ManualLottoGeneratingStrategy strategy
                = new ManualLottoGeneratingStrategy(Arrays.asList(6, 5, 4, 3, 2, 1));
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(strategy.generate()).isEqualTo(lottoNumbers);
    }
}
