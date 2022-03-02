package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoCountTest {

    @DisplayName("구입 금액이 null 인 경우")
    @Test
    void payment_null() {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(null,0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 로또 개수가 음수인 경우")
    @Test
    void lotto_count_negative() {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(new Payment(5000),-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 로또 개수가 총 로또 개수보다 많은 경우")
    @Test
    void manual_count_more_total_count() {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(new Payment(5000),6);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동 로또 개수 구하기")
    @Test
    void auto_count() {
        LottoCount lottoCount = new LottoCount(new Payment(5000),3);

        assertEquals(lottoCount.getAutoCount(), 2);
    }
}