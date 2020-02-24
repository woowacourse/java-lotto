package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidLottoException;

public class LottoTest {
    private static final int LOTTO_SIZE = 6;
    List<Number> numbers;

    @BeforeEach
    void initLotto() {
        numbers = Arrays.asList(
                LottoFactory.of("3"),
                LottoFactory.of("4"),
                LottoFactory.of("5"),
                LottoFactory.of("6"),
                LottoFactory.of("7"),
                LottoFactory.of("8")
        );
    }

    @Test
    @DisplayName("정상작동 : 로또 하나의 사이즈 6")
    void size() {
        assertThat(new Lotto(numbers));
    }

    @Test
    @DisplayName("오작동 : 로또 하나의 사이즈 5")
    void sizeException() {
        numbers = numbers.subList(0, LOTTO_SIZE - 1);
        assertThatThrownBy(() -> {
            new Lotto(numbers);
        }).isInstanceOf(InvalidLottoException.class)
                .hasMessageMatching("로또는 " + LOTTO_SIZE + "개의 수를 가져야 합니다.");
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다.")
    void numberDuplication() {
        numbers.set(0, numbers.get(1));
        assertThatThrownBy(() -> {
            new Lotto(numbers);
        }).isInstanceOf(InvalidLottoException.class)
                .hasMessageMatching("로또 번호는 중복 될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 두개를 비교하여 같은 수의 개수를 반환하는지")
    void win() {
        List<Number> win = Arrays.asList(
                LottoFactory.of("3"),
                LottoFactory.of("43"),
                LottoFactory.of("5"),
                LottoFactory.of("6"),
                LottoFactory.of("7"),
                LottoFactory.of("8")
        );
        assertThat(new Lotto(numbers).compare(new Lotto(win))).isEqualTo(5);
    }

    @Test
    @DisplayName("contains에 인자로 들어온 값이 있는지를 잘 반환")
    void bonus() {
        assertThat(
                new Lotto(numbers).contains(LottoFactory.of("3"))
        ).isTrue();
    }
}
