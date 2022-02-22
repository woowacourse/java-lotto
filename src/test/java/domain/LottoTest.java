package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

	@Test
	@DisplayName("로또 내부의 숫자는 1~45 사이의 숫자여야한다.")
	void checkLottoNumber() {
		//given
		List<Integer> missLotto = Arrays.asList(1, 5, 9, 11, 90, 100);

		//then
		assertThatThrownBy(() -> new Lotto(missLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 1 ~ 45의 숫자여야 합니다");
	}

	@Test
	@DisplayName("로또 팩토리는 랜덤한 숫자 6개를 뽑아야한다.")
	void checkLottoSize() {
		//given
		List<Integer> missLotto = Arrays.asList(1, 5, 9, 11);

		//then
		assertThatThrownBy(() -> new Lotto(missLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("로또 번호는 중복된 숫자가 존재할 수 없다.")
	void checkDuplicateLottoNumber() {
		//given
		List<Integer> duplicateLotto = Arrays.asList(1, 5, 9, 11, 11, 5);

		//then
		assertThatThrownBy(() -> new Lotto(duplicateLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 숫자를 입력할 수 없습니다");
	}
}