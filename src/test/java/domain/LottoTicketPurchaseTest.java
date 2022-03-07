package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketPurchaseTest {

	@Test
	@DisplayName("로또 번호 : 6개가 아닌 개수의 번호를 입력 한 경우 예외 발생")
	void countOfNumbersMustBeSix() {
		assertThatThrownBy(() -> LottoTicket.byManual(List.of(1, 2, 3, 4, 5, 6, 7)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개의 숫자만 허용됩니다.");
	}

	@Test
	@DisplayName("로또 번호 : 중복되는 숫자를 입력 한 경우 예외 발생")
	void duplicateInNumbers() {
		assertThatThrownBy(() -> LottoTicket.byManual(List.of(1, 2, 3, 4, 5, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자들은 중복일 수 없습니다.");
	}
}
