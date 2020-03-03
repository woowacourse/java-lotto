package domain;

import Lotto.domain.Lotto;
import Lotto.domain.LottoManager;
import Lotto.domain.LottoNumber;
import Lotto.domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoManagerTest {

    @Test
    @DisplayName("manual로또가 null일 때 concat에서 에러가 나지 않는 것 확인")
    void inputInit() {
        Lotto autoLotto = new Lotto(Arrays.asList(
                LottoNumber.of(34), LottoNumber.of(22), LottoNumber.of(12),
                LottoNumber.of(23), LottoNumber.of(11), LottoNumber.of(1)));
        Lottos autoLottos = new Lottos(Arrays.asList(autoLotto));
        Lottos manualLottos = null;
        Lottos allLottos = LottoManager.concatLottos(autoLottos, manualLottos);
        assertThat(autoLottos).isEqualTo(allLottos);
    }
}
