package lotto.domain.ticket.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.ticket.generator.RandomTicketGenerator;

class RandomTicketGeneratorTest {

	@DisplayName("렌덤 로또 번호 생성 테스트")
	@Test
	void generate() {
		final List<Integer> numbers = (new RandomTicketGenerator()).generate();

		assertThat(numbers.size()).isEqualTo(6);
	}

}
