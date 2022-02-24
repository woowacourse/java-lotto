package domain;

<<<<<<< HEAD
import domain.Lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ExceptionMessage;
=======
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
<<<<<<< HEAD
>>>>>>> 5d27d71 (feat: LottoNumber 객체 생성)
=======
import utils.ExceptionMessage;
>>>>>>> d722001 (refactor: 예외 메세지 별도 클래스로 분리)

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호 숫자가 1~45 이외의 숫자일 때 예외를 발생한다.")
    void 로또_넘버_0_예외_테스트(int lottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
<<<<<<< HEAD
<<<<<<< HEAD
                .hasMessage(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
=======
                .hasMessage(LottoNumber.LOTTO_NUMBER_OUT_OF_BOUND);
>>>>>>> 5d27d71 (feat: LottoNumber 객체 생성)
=======
                .hasMessage(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
>>>>>>> d722001 (refactor: 예외 메세지 별도 클래스로 분리)

    }
}
