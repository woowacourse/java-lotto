package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto(LottoNumbers.ofRandomNumbers());

        assertThat(lotto.getNumbers().getValues().size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto(LottoNumbers.ofRandomNumbers());
        List<Integer> numberValues = lotto.getNumbers().getValues();

        for (int index = 0; index < (numberValues.size() - 1); index++) {
            assertThat(numberValues.get(index)).isLessThan(numberValues.get(index + 1));
        }
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    @Test
    void unit_exception() {
        assertThatThrownBy(() -> Lotto.countAvailableTickets(Money.from("1500")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력하세요");
    }
}
