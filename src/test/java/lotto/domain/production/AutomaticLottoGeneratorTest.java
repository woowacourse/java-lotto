package lotto.domain.production;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AutomaticLottoGeneratorTest {
	@DisplayName("로또를 에러 없이 잘 생성 하는지")
	@Test
	void createLotto() {
		AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();
		assertThat(automaticLottoGenerator.createLotto()).isNotNull();
	}
}