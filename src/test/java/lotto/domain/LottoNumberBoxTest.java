package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberBoxTest {

    @Test
    @DisplayName("LottoNumberBox가 예상한대로 준비돼있는 지 확인")
    void get() {
        assertThat(LottoNumberBox.get().size()).isEqualTo(45);
    }
}