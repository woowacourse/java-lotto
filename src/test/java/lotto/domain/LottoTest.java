package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 생성 검증")
    void testLotto() {
        assertThat(new Lotto(List.of(1,2,3,4,5,6))).isNotNull();


    }

    @Test
    @DisplayName("로또 번호가 6개가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_sizeException() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
            .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    @DisplayName("로또 번호 가 1에서 45 사이가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_rangeException() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,100)))
            .isInstanceOf(IllegalArgumentException.class);
    }

}