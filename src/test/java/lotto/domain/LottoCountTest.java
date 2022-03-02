package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoCountTest {

    @DisplayName("로또 개수가 음수인 경우")
    @Test
    void lotto_count_negative() {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 수동 개수가 음수인 경우

    // TODO: 총 개수보다 수동 개수가 더 많은 경우

    // TODO: 자동 개수 구하기
}