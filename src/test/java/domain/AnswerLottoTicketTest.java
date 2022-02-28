package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerLottoTicketTest {

	@Test
	@DisplayName("지난주 당첨 번호와 보너스 번호가 중복되는 경우 예외 발생")
	void duplicateInBonusNumber() {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 6), 6))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
