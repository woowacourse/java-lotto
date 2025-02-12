package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoManagerTest {
    private LottoManager lottoManager;

    @BeforeEach
    void init() {
        lottoManager = new LottoManager();
    }

    @DisplayName("구입금액이 1000원으로 나누어 떨어지지 않으면 예외를 던진다")
    @Test
    void 구입금액이_1000원으로_나누어_떨어지지_않으면_예외를_던진다() {
        assertThatThrownBy(() ->
            lottoManager.purchase(500)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 1000원으로 나누어져야 합니다.");
    }

    @DisplayName("구입금액이 양수가 아니라면 예외를 던진다")
    @Test
    void 구입금액이_양수가_아니라면_예외를_던진다() {
        assertThatThrownBy(() ->
                lottoManager.purchase(-1000)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 양수여야 합니다.");
    }
}