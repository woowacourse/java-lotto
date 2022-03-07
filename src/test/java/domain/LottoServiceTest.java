package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;

class LottoServiceTest {
    @ParameterizedTest(name = "생성 수 : {0}")
    @ValueSource(ints = {1, 10, 100})
    @DisplayName("자동 로또 생성")
    void createAuto(int quantity) {
        // given
        Lottos autoLottos = LottoService.createAuto(quantity);

        // when
        List<Lotto> lottos = autoLottos.getLottos();
        int actual = lottos.size();

        // then
        assertThat(actual).isEqualTo(quantity);
    }

    @Test
    @DisplayName("수동 로또 생성 테스트")
    void createManual() {
        // given
        int expected = 3;
        System.setIn(new ByteArrayInputStream("1, 2, 3, 4, 5, 6\n2, 3, 4, 5, 6, 7\n1, 3, 5, 7, 8, 45".getBytes()));
        List<List<Integer>> scannedLottoNumbers = InputView.scanManualLottoNumbers(3);

        // when
        Lottos autoLottos = LottoService.createManual(scannedLottoNumbers);
        List<Lotto> lottos = autoLottos.getLottos();
        int actual = lottos.size();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
