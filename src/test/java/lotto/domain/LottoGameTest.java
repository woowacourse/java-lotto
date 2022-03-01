package lotto.domain;

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
}
