package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {

	@Test
	void 보너스_중복_성공(){
		Lotto lotto = new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")));
		LottoNumber bonusNumber = new LottoNumber("7");
		assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
			.doesNotThrowAnyException();
	}

	@Test
	void 보너스_중복_실패(){
		Lotto lotto = new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")));
		LottoNumber bonusNumber = new LottoNumber("6");
		assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
