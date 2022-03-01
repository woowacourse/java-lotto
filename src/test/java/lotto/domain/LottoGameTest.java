package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoGameTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10, 14})
    @DisplayName("수동 구매 개수를 생성한다.")
    void makeManualLottoCount(int count) {
        LottoGame lottoGame = new LottoGame(new PurchaseAmount(14_000), count);
        assertThat(lottoGame.getManualLottoCount()).isEqualTo(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 16, 17})
    @DisplayName("구입 금액이 수동 구매 개수를 만족하지 못하는 경우 에러를 발생시킨다.")
    void throwExceptionWhenManualLottoCountIsBigger(int count) {
        assertThatThrownBy(() -> new LottoGame(new PurchaseAmount(14_000), count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액으로 살 수 있는 수량이어야 합니다.");
    }

    @Test
    @DisplayName("수동으로 구매할 번호를 입력받아 생성한다.")
    void makeManualLottos() {
        LottoGame lottoGame = new LottoGame(new PurchaseAmount(14_000), 3);

        List<List<Integer>> inputLottos = new ArrayList<>();
        inputLottos.add(List.of(8, 21, 23, 41, 42, 43));
        inputLottos.add(List.of(3, 5, 11, 16, 32, 38));
        inputLottos.add(List.of(7, 11, 16, 35, 36, 44));

        lottoGame.makeManualLottos(inputLottos);
        assertThat(lottoGame.getLottos().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동으로 구매할 로또 개수가 입력 개수와 다르면 예외를 발생시킨다.")
    void throwExceptionWhenManulLottoCountNotEqual() {
        LottoGame lottoGame = new LottoGame(new PurchaseAmount(14_000), 3);

        List<List<Integer>> inputLottos = new ArrayList<>();
        inputLottos.add(List.of(8, 21, 23, 41, 42, 43));
        inputLottos.add(List.of(3, 5, 11, 16, 32, 38));

        assertThatThrownBy(() -> lottoGame.makeManualLottos(inputLottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동으로 구매할 로또 수와 같은 개수의 로또를 입력해주세요.");
    }
}
