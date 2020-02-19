package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottoFactoryTest {
	@Test
	void 생성_테스트(){
		assertThat(LottoFactory.create());
	}

}