package model;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoUtil.generateTestLotto;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    void 로또를_생성_시_올바르게_생성되는지_확인한다() {
        CustomLottoNumberPicker customLottoNumberPicker = new CustomLottoNumberPicker();
        LottoGenerator lottoGenerator = new LottoGenerator(customLottoNumberPicker);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        lottoNumbers.forEach(customLottoNumberPicker::addValue);
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto).isEqualTo(generateTestLotto(lottoNumbers));
    }
}
