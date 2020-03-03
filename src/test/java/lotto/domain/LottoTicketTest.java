package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    @DisplayName("중복숫자가 있을 때")
    void validateDistinctNumberTest_exist_number() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> invalidNumbers = numberGenerator.generateNumbers("1,2,3,4,5,5");
        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("로또 숫자가 6개가 아닐 때")
    void validateNumberCountTest_lotto_number_not_six() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> invalidNumbers = numberGenerator.generateNumbers("1,2,3,4,5");

        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_COUNT_NOT_SIX.getMessage());
    }

    @Test
    @DisplayName("올바른 로또 숫자일 때")
    void validateDistinctNumberTest_right_number() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> validNumbers = numberGenerator.generateNumbers("1,2,3,4,5,45");

        new LottoTicket(validNumbers);
    }
}
