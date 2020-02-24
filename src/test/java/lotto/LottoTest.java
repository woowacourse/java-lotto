package lotto;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 로또_번호_갯수_6개인지_확인(){
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
    void 중복된_로또_번호가_있는지_확인(){
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
    void 로또_숫자_범위_벗어난_경우_예외_발생() {
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
