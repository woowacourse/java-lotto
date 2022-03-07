package lotto.domain.lotto;

import lotto.controller.LottoController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    public void 로또번호들_생성_성공(int count) {
        new Lottos(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    public void 로또번호들_생성_실패(int count) {
        assertThatThrownBy(() -> new Lottos(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 구매 값을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6"})
    public void 수동로또_추가_성공(String value) {
        LottoController lottoController = new LottoController();
        Lottos lottos = new Lottos();
        lottos.addLotto(lottoController.createLottoByNumbers(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    public void 수동로또_추가_실패(String value) {
        LottoController lottoController = new LottoController();
        Lottos lottos = new Lottos();
        assertThatThrownBy(() -> lottos.addLotto(lottoController.createLottoByNumbers(value)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 숫자 입력입니다.");
    }
}
