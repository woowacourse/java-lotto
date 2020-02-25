package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosFactoryTest {

    @DisplayName("랜덤 로또 개수 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void makeAutoLottos(int count) {
        List<String> strings = new ArrayList<>();
        Lottos autoLottos = LottosFactory.createLottos(strings, new LottoCount(count));
        assertThat(autoLottos.getCount()).isEqualTo(new LottoCount(count));
    }

    @DisplayName("수동 로또 생성")
    @Test
    void makeManualLottos() {
        List<String> strings = new ArrayList<>();
        strings.add("1, 2, 3, 4, 5, 6");
        strings.add("2, 3, 4, 5, 6, 7");
        LottoCount lottoCount = new LottoCount(2, 2);
        Lottos manualLotto = LottosFactory.createLottos(strings, lottoCount);
        assertThat(manualLotto.getCount())
            .isEqualTo(lottoCount);
    }

    @DisplayName("자동 수동 로또 생성")
    @Test
    void makeLottos() {
        List<String> strings = new ArrayList<>();
        strings.add("1, 2, 3, 4, 5, 6");
        strings.add("2, 3, 4, 5, 6, 7");
        LottoCount lottoCount = new LottoCount(4, 2);
        Lottos manualLotto = LottosFactory.createLottos(strings, lottoCount);
        assertThat(manualLotto.getCount())
            .isEqualTo(lottoCount);
    }
}
