package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.lottotickets.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("생성된 로또 번호가 46 이상일시 에러를 발생한다.")
    void checkNumber_OverThanMaxNumber() {
        assertThatThrownBy(
                () -> new LottoNumber(46)
        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호가 저장되는지 확인한다.")
    void generateNumber_Test() {
        final LottoNumber lottoNumber = new LottoNumber(4);

        assertThat(lottoNumber.get()).isEqualTo(4);
    }
}
