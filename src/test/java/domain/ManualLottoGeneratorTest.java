package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoGeneratorTest {

    @Test
    @DisplayName("입력 숫자에 따라 수동로또가 생성되는 지 테스트")
    void generateManualLottoTest() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(new String[]{"1", "2", "3", "4", "5", "6"});
        Lotto lotto = manualLottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).isEqualTo(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
    }
}