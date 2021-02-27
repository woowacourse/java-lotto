package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입한 로또 매수만큼 로또 생성")
    void createLottos() {
        int expectedLottoSize = 14;
        List<String> manualLottoNumbers = new ArrayList<>(Collections.singletonList("1,2,3,4,5,6"));
        Lottos manualLottos = LottoGenerator.createManualLottos(manualLottoNumbers);
        Lottos autoLottos = LottoGenerator.createAutoLottos(13);
        Lottos purchasedLottos = new Lottos(manualLottos, autoLottos);
        assertThat(purchasedLottos.getSize()).isEqualTo(expectedLottoSize);
    }
}