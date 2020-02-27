package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoSizeTest {
    @Test
    void validateLottoSize_유효한_로또_개수일_때() {
        PaidPrice paidPrice = new PaidPrice("2000");
        int validateLottoSize = 1;
        new LottoSize(paidPrice, validateLottoSize);
    }

    @Test
    void validateLottoSize_구입한_로또_개수를_초과했을_때() {
        PaidPrice paidPrice = new PaidPrice("2000");
        int invalidateLottoSize = 3;
        assertThatThrownBy(() -> new LottoSize(paidPrice, invalidateLottoSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 개수입니다.");
    }

    @Test
    void validateLottoSize_음수를_입력했을_때() {
        PaidPrice paidPrice = new PaidPrice("2000");
        int invalidateLottoSize = -1;
        assertThatThrownBy(() -> new LottoSize(paidPrice, invalidateLottoSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 개수입니다.");
    }

    @Test
    void validateNumber_문자를_입력했을_때() {
        PaidPrice paidPrice = new PaidPrice("2000");
        String invalidateLottoSize = "문자";
        assertThatThrownBy(() -> new LottoSize(paidPrice, invalidateLottoSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 숫자입니다.");
    }
}
