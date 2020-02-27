package lotto;

import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    @DisplayName("생성된 로또 번호가 6개인지 확인")
    void checkLottoSizeSixTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lotto = new ArrayList<>();
            lotto.add(new LottoNumber(1));
            lotto.add(new LottoNumber(1));
            lotto.add(new LottoNumber(2));
            lotto.add(new LottoNumber(3));
            lotto.add(new LottoNumber(4));
            new Lotto(lotto);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    @DisplayName("생성된 로또 번호 중 중복된 번호가 있는지 확인")
    void checkDuplicatedNumberTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lotto = new ArrayList<>();
            lotto.add(new LottoNumber(1));
            lotto.add(new LottoNumber(1));
            lotto.add(new LottoNumber(2));
            lotto.add(new LottoNumber(3));
            lotto.add(new LottoNumber(4));
            lotto.add(new LottoNumber(5));
            new Lotto(lotto);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 로또 번호가 입력되었습니다.");
    }

    @Test
    @DisplayName("생성된 로또 번호 중 1~45까지의 범위를 벗어난 번호가 있는 지 확인")
    void checkLottoNumberRangeTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lotto = new ArrayList<>();
            lotto.add(new LottoNumber(0));
            lotto.add(new LottoNumber(1));
            lotto.add(new LottoNumber(2));
            lotto.add(new LottoNumber(3));
            lotto.add(new LottoNumber(4));
            lotto.add(new LottoNumber(5));
            new Lotto(lotto);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }
}
