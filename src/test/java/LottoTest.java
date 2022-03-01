import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 성공")
    void lotto_generate_success() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getLottoNumbers()).containsExactly(1, 2, 3, 4, 5, 6);

    }

    @Test
    @DisplayName("로또 숫자 1~45 사이 아닐 시 예외 발생")
    void check_range_fail() {

        assertThatThrownBy(
            () -> new Lotto(List.of(1, 2, 3, 4, 5, 46))
        ).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("로또 숫자가 7개 이상일 시 예외 발생")
    void check_size_fail() {
        assertThatThrownBy(
            () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복이 있을 시 예외 발생")
    void check_unique_fail() {
        assertThatThrownBy(
            () -> new Lotto(List.of(1, 2, 3, 4, 5, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }


}
