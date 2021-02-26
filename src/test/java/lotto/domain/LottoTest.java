package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {

    @Test
    @DisplayName("로또 생성하기")
    void createLotto() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isEqualTo(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    @DisplayName("로또는 총 6개의 숫자로 이루어져야한다.")
    void lottoSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5)));
    }
}
