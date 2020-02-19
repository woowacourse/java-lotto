package lotto;

import domain.LottoFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoFactoryTest {
    @Test
    void 범위_밖의_로또_번호_생성_시_예외_발생() {
        assertThatThrownBy(() -> {
            LottoFactory.createLottoNumber(0);
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    void 로또_한장_생성(){
        List<Integer> lotto = LottoFactory.createOneLotto();
        assertThat(lotto.size()).isEqualTo(6);
    }
}
