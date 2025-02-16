package model;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoUtil.generateTestLotto;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class LottoGeneraterTest {

    @Test
    void 로또_생성을_테스트합니다() {
        CustomLottoNumberPicker customLottoNumberPicker = new CustomLottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(customLottoNumberPicker);
        int[] lottoNumbers = {1, 2, 3, 4, 5, 6};
        Arrays.stream(lottoNumbers).forEach(customLottoNumberPicker::addValue);
        Lotto lotto = lottoGenerater.generateLotto();
        assertThat(lotto).isEqualTo(generateTestLotto(lottoNumbers));
    }
}
