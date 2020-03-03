package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTicketTest {
    @Test
    @DisplayName("보너스볼이 나오지 않은 숫자일 때")
    void validateDistinctBonusTest_distinct_bonus_number() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> winningNumbers = numberGenerator.generateNumbers("1,2,3,4,5,6");
        LottoNumber validBonusNumber = new LottoNumber("7");
        new WinningLottoTicket(winningNumbers, validBonusNumber);
    }

    @Test
    @DisplayName("보너스볼이 이미 나온 숫자일 때")
    void validateDistinctBonusTest_not_distinct_bonus_number() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> winningNumbers = numberGenerator.generateNumbers("1,2,3,4,5,6");
        LottoNumber invalidBonusNumber = new LottoNumber("6");
        assertThatThrownBy(() -> new WinningLottoTicket(winningNumbers, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER
                        .getMessage());
    }

    @Test
    @DisplayName("맞는 숫자 카운트 함수 테스트")
    void countMatchedNumberTest() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> winningNumbers = numberGenerator.generateNumbers("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        WinningLottoTicket winningLotto = new WinningLottoTicket(winningNumbers, bonusNumber);

        List<LottoNumber> userLottoNumbers = numberGenerator.generateNumbers("1,2,3,4,5,10");
        LottoTicket userLottoTicket = new LottoTicket(userLottoNumbers);
        assertThat(winningLotto.countMatchedNumber(userLottoTicket))
                .isEqualTo(5);
    }
}
