package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @DisplayName("로또 번호 생성하기")
    @Test
    void create() {
        LottoNumber lottoNumber = new LottoNumber(3);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(3));
    }

    @DisplayName("로또 번호는 1~45사이의 숫자이어야 한다.")
    @Test
    void checkNumberInProperRange() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(0));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(46));
    }

    @DisplayName("당첨 로또는 보너스볼과 숫자가 중복되어선 안된다.")
    @Test
    void duplicate(){
        LottoNumber bonusBall = new LottoNumber(3);
        LottoTicket ticket = new LottoTicket(Arrays.asList(1,2,3,4,5,6));
        assertThatIllegalArgumentException()
            .isThrownBy(()->bonusBall.validateDuplicate(ticket));
    }
}
