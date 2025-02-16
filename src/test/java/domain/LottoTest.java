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
        void 로또_번호_범위가_다르면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                Lotto.from(List.of(1,2,3,4,5,46));
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 로또_번호가_중복되면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                Lotto.from(List.of(1,2,3,4,5,5));
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }

        @Test
        void 로또_번호_개수가_다르면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                Lotto.from(List.of(1,2,3,4,5));
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }
    }
}