package lottogame.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.of(8), LottoNumber.of(21),
                LottoNumber.of(23), LottoNumber.of(41),
                LottoNumber.of(42), LottoNumber.of(43));
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void constructor1() {
        List<LottoNumber> newLottoNumbers = Arrays.asList(
                LottoNumber.of(8), LottoNumber.of(21),
                LottoNumber.of(23), LottoNumber.of(41),
                LottoNumber.of(42), LottoNumber.of(43));
        Lotto newLotto = new Lotto(newLottoNumbers);
        assertEquals(newLotto, lotto);
    }

    @Test
    @DisplayName("중복되는 숫자가 있으면 예외가 발행하는지 확인")
    void constructor2() {
        List<LottoNumber> newLottoNumbers = Arrays.asList(
                LottoNumber.of(8), LottoNumber.of(21),
                LottoNumber.of(23), LottoNumber.of(41),
                LottoNumber.of(42), LottoNumber.of(42));
        assertThatThrownBy(() -> new Lotto(newLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호는 서로 달라야합니다.");
    }

    @Test
    @DisplayName("로또를 다른 로또와 잘 비교하는지 확인")
    void match() {
        List<LottoNumber> newLottoNumbers = Arrays.asList(
                LottoNumber.of(8), LottoNumber.of(21),
                LottoNumber.of(23), LottoNumber.of(1),
                LottoNumber.of(2), LottoNumber.of(3));
        Lotto newLotto = new Lotto(newLottoNumbers);
        assertEquals(3, lotto.match(newLotto));
    }

    @Test
    @DisplayName("로또가 로또번호를 포함하는지 확인")
    void contains() {
        assertTrue(lotto.contains(LottoNumber.of(8)));
        assertFalse(lotto.contains(LottoNumber.of(7)));
    }
}
