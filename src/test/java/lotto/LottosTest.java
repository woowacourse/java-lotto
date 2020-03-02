package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("수동으로 로또 번호 입력시 정상적으로 Lottos 에 추가되는지 확인")
    void addLottoWithManualStringArrayInputTest() {
        String[] inputLottoNumbers = {"1", "2", "3", "4", "5", "6"};
        LottoGenerator lottoGenerator = new ManualLottoGenerator(inputLottoNumbers);
        Lotto lotto = lottoGenerator.generateLotto();

        Lottos lottos = new Lottos();
        lottos.addLotto(lotto);
        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }
}
