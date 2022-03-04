package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoMachineTest {

    private static final String ERROR_NON_PURCHASABLE = "[ERROR] 구입 금액으로 살 수 있는 수량이어야 합니다.";

    @ParameterizedTest(name = "[{index}] {0}개")
    @ValueSource(ints = {0, 3, 10, 14})
    @DisplayName("수동 구매 개수를 생성한다.")
    void makeManualLottoCount(int count) {
        final LottoMachine lottoGame = new LottoMachine(new PurchaseAmount(14_000), count);

        assertThat(lottoGame.getManualLottoCount()).isEqualTo(count);
    }

    @ParameterizedTest(name = "[{index}] {0}개")
    @ValueSource(ints = {15, 16, 17})
    @DisplayName("구입 금액이 수동 구매 개수를 만족하지 못하는 경우 에러를 발생시킨다.")
    void throwExceptionWhenManualLottoCountIsBigger(int count) {
        assertThatThrownBy(() -> new LottoMachine(new PurchaseAmount(14_000), count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NON_PURCHASABLE);
    }

    @Test
    @DisplayName("수동/자동 로또를 생성한다.")
    void makeManualAndAutoLottos() {
        final PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);
        final LottoMachine lottoGame = new LottoMachine(purchaseAmount, 3);

        final List<List<Integer>> inputLottos = new ArrayList<>();
        inputLottos.add(List.of(8, 21, 23, 41, 42, 43));
        inputLottos.add(List.of(3, 5, 11, 16, 32, 38));
        inputLottos.add(List.of(7, 11, 16, 35, 36, 44));

        final Lottos lottos = lottoGame.makeManualAndAutoLottos(inputLottos);

        assertThat(lottos.size()).isEqualTo(14);
    }
}
