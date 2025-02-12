package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호 생성 테스트")
    @Test
    void 로또_번호_생성_테스트() {
        List<Integer> lotto = new Lotto().getLotto();
        Assertions.assertThat(lotto.size()).isEqualTo(6);
        for (int number : lotto) {
            Assertions.assertThat(number).isBetween(1, 45);
        }
    }
}