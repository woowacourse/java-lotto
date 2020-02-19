package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.exceptions.LottoNumberRangeException;

public class BonusNumberTest {
	@Test
	public void initTest() {
		BonusNumber bonusNumber = new BonusNumber(1);
		assertThat(bonusNumber).isNotNull();
		assertThatThrownBy(() -> new BonusNumber(46))
			.isInstanceOf(LottoNumberRangeException.class)
		.hasMessageContaining("보너스 넘버");
	}
}