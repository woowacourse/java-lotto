package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateTicketUtilsTest {

    @Test
    @DisplayName("당첨 번호가 숫자인지 확인")
    void validate_winning_balls_number() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()-> ValidateTicketUtils.validateWinningBallsNumber(inputValue))
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("당첨 번호가 숫자가 아닐때 오류 확인")
    void throw_validate_winning_balls_number() {
        String[] inputValue = { "1","2","3","4","5","가"};

        assertThatThrownBy(()-> ValidateTicketUtils.validateWinningBallsNumber(inputValue))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("숫자가 6개일때 테스트")
    void validate_winning_balls_length() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()-> ValidateTicketUtils.validateWinningBallsLength(inputValue))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자가 6개가 아닐때 테스트")
    void throw_validate_winning_balls_length() {
        String[] inputValue = { "1","2","3","4","5"};

        assertThatThrownBy(()-> ValidateTicketUtils.validateWinningBallsLength(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복이 없을경우 테스트")
    void validate_duplicated_winning_balls() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()-> ValidateTicketUtils.validateDuplicatedWinningBalls(inputValue))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복이 있을경우 테스트")
    void throw_validate_duplicated_winning_balls() {
        String[] inputValue = { "1","2","3","4","5","5"};

        assertThatThrownBy(()-> ValidateTicketUtils.validateDuplicatedWinningBalls(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}