package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("로또를 옳바르게 생성하는지 테스트한다.")
public class LottoNumbersTest {

    @Test
    @DisplayName("올바른 크기의 로또 생성")
    void createLotto_makeRightLotto() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }

}
