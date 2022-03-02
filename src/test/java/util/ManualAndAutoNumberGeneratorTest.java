package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.ManualLotto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualAndAutoNumberGeneratorTest {

    @Test
    @DisplayName("수동 로또 생성 확인 테스트")
    public void manualLottoNumberTest() {
        ManualLotto manualLotto = new ManualLotto(List.of(1, 2, 3, 4, 5, 6));
        List<ManualLotto> manualLottos = List.of(manualLotto);
        ManualAndAutoNumberGenerator manualAndAutoNumberGenerator = new ManualAndAutoNumberGenerator(manualLottos);
        assertThat(manualAndAutoNumberGenerator.generate()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

}
