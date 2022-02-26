package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    @Test
    void unit_exception() {
        assertThatThrownBy(() -> Lotto.purchase(Money.from("1500")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력하세요");
    }

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers().getSize()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto();
        LottoNumbers numbers = lotto.getNumbers();
        for (int index = 0; index < (numbers.getSize() - 1); index++) {
            assertThat(numbers.get(index).compareTo(numbers.get(index + 1))).isEqualTo(-1);
        }
    }
}
