package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Nested
    class 로또_번호_테스트 {
        @Test
        void 로또_번호_범위_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 로또_번호_구분자_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1/2/3/4/5/6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

        @Test
        void 로또_번호_중복_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }

        @Test
        void 로또_번호_길이_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

        @Test
        void 로또_번호_정수_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,a");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

    }

    @Nested
    class 보너스_번호_테스트 {

        @Test
        void 보너스_번호_범위_테스트() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when-then
            assertThatThrownBy(() -> {
                lotto.validateBonus("46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 보너스_번호_중복_테스트() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when-then
            assertThatThrownBy(() -> {
                lotto.validateBonus("6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }
    }

    @Test
    void countMatchNumbers() {

    }

}