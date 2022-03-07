package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    @Test
    @DisplayName("올바른 크기의 로또 생성")
    void createLotto_makeRightLotto() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }
}
