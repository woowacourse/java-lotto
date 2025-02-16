package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SplitterTest {

    @DisplayName("콤마로 구분하면 올바르게 분리된다.")
    @Test
    void 콤마로_구분하면_올바르게_분리된다() {

        //given
        String numbersBeforeSplit = "1,2,3,4,5";

        //when
        List<String> numbers = Splitter.splitByComma(numbersBeforeSplit);

        //then
        assertThat(numbers.size()).isEqualTo(5);
    }

    @DisplayName("콤마로 구분하지 않으면 예외를 발생한다.")
    @Test
    void 콤마로_구분하지_않으면_예외를_발생한다() {

        //given
        String numbersBeforeSplit = "1.2.3.4.5";

        //when & then
        assertThatThrownBy(() -> Splitter.splitByComma(numbersBeforeSplit))
                .isInstanceOf(LottoException.class);

    }

}
