package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@DisplayName("수동으로 구매할 로또 수가 0 보다 작으면 예외를 반환한다")
	@Test
	void bound_exception_under() {
		List<String[]> manualLottoNumbers = new ArrayList<>();

		assertThatThrownBy(() -> {
			Lottos.purchase(10, -100, manualLottoNumbers);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 개수는 0 이상, 총 구매 개수 이하로 입력해 주세요");
	}

	@DisplayName("수동으로 구매할 로또 수가 총 구매 개수보다 크면 예외를 반환한다")
	@Test
	void bound_exception_over() {
		List<String[]> manualLottoNumbers = new ArrayList<>();

		assertThatThrownBy(() -> {
			Lottos.purchase(10, 11, manualLottoNumbers);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 개수는 0 이상, 총 구매 개수 이하로 입력해 주세요");
	}
}
