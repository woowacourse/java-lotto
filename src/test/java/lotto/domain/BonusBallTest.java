package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class BonusBallTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        Set<LottoNo> numbers = IntStream.range(1, 7)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet());
        lotto = new Lotto(numbers);
    }

    @DisplayName("보너스 볼을 가지고 있는 경우")
    @Test
    void contains() {
        BonusBall bonusBall = new BonusBall("3");

        assertThat(bonusBall.contains(lotto)).isTrue();
    }

    @DisplayName("보너스 볼을 가지고 있지 않는 경우")
    @Test
    void contains2() {
        BonusBall bonusBall = new BonusBall("9");

        assertThat(bonusBall.contains(lotto)).isFalse();
        assertThat(bonusBall.contains(null)).isFalse();
    }
}
