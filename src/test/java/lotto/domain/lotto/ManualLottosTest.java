package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.controller.LottoController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ManualLottosTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6"})
    public void 수동로또_추가_성공(String value) {
        LottoController lottoController = new LottoController();
        ManualLottos manualLottos = new ManualLottos();
        manualLottos.add(lottoController.createLottoByNumbers(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    public void 수동로또_추가_실패(String value) {
        LottoController lottoController = new LottoController();
        ManualLottos manualLottos = new ManualLottos();
        assertThatThrownBy(() -> manualLottos.add(lottoController.createLottoByNumbers(value)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 숫자 입력입니다.");
    }
}
