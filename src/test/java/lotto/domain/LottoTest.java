package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("중복되거나 6개의 숫자가 아닌 경우 예외를 발생시킨다")
    void testLottoNumbers() {

        assertThatThrownBy(() ->
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(5)
                )))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정수 리스트를 입력받아 로또 객체를 생성한다")
    void testLottoOfIntegers() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getLottoNumbers()).contains(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
    }
}
