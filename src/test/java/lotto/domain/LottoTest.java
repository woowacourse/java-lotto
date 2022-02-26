package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoTest {

    public static List<LottoNumber> createLottoNumbers(int... number) {
        return Arrays.stream(number)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    @DisplayName("LottoNumbers가 null이 들어오는 경우 에러 발생")
    @Test
    void nullLottoNumubersException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Lotto(null))
                .withMessage("[ERROR] Lotto는 null로 생성할 수 없습니다.");
    }

    @DisplayName("구매 로또 생성시 숫자가 6개가 입력되지 않으면 에러 발생")
    @Test
    void lottoCreateExceptionBySize() {
        final List<LottoNumber> lottoNumbers = createLottoNumbers(1, 2, 3, 4, 5);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
    }

    @DisplayName("구매 로또가 중복될 경우 예외가 발생해야 한다.")
    @Test
    void duplicateLottoException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(createLottoNumbers(1, 1, 1, 1, 1, 1)))
                .withMessage("[ERROR] 로또 넘버는 중복될 수 없습니다.");
    }

    @Nested
    @DisplayName("번호를 포함하는지 확인할 수 있다.")
    class ContainNumber {

        private Lotto lotto;

        @BeforeEach
        void setUp() {
            lotto = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6));
        }

        @DisplayName("보너스 넘버를 포함하는 경우")
        @Test
        void containNumberTrue() {
            final LottoNumber lottoNumber = LottoNumber.valueOf(1);
            assertTrue(lotto.containNumber(lottoNumber));
        }

        @DisplayName("보너스 넘버를 포함하지 않는 경우")
        @Test
        void containNumberFalse() {
            final LottoNumber lottoNumber = LottoNumber.valueOf(7);
            assertFalse(lotto.containNumber(lottoNumber));
        }
    }

    @DisplayName("로또 맞은 개수를 계산한다.")
    @Test
    void matchLotto() {
        final Lotto lotto = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6));
        final Lotto compareLotto = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.matchingCounts(compareLotto)).isEqualTo(5);
    }

    @DisplayName("로또 번호를 Integer로 받아 생성할 수 있다.")
    @Test
    void createByIntegerNumbers() {
        final List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.toList());

        assertThat(Lotto.from(numbers)).isInstanceOf(Lotto.class);
    }
}
