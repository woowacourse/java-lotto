package lotto.domain;

import lotto.domain.lottogenerator.LottoGeneratorManual;
import lotto.domain.lottogenerator.LottoNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorManualTest {
	Customer customer = new Customer(5000, 2);

	@DisplayName("2개의 로또 번호 입력시 생성 테스트")
	@Test
	void generator1() {
		customer.setManualLottoNumbers("1,2,3,4,5,6\n11,12,13,14,15,16");

		List<Lotto> result = new ArrayList<>();
		Lotto lotto1 = new Lotto(IntStream.range(1, 7)
				.boxed()
				.map(LottoNo::toLottoNo)
				.collect(Collectors.toList()));
		Lotto lotto2 = new Lotto(IntStream.range(11, 17)
				.boxed()
				.map(LottoNo::toLottoNo)
				.collect(Collectors.toList()));
		result.add(lotto1);
		result.add(lotto2);

		assertThat(new LottoGeneratorManual().generator(customer)).isEqualTo(result);
	}

	@DisplayName("공란의 로또 넘버가 입력되었을 경우 생성 테스트 확인")
	@Test
	void generator2() {
		Customer customerManualCountZero = new Customer(4000, 0);
		customerManualCountZero.setManualLottoNumbers("");

		List<Lotto> result = new ArrayList<>();
		assertThat(new LottoGeneratorManual().generator(customerManualCountZero))
				.isEqualTo(result);
	}

	@DisplayName("수동로또번호 입력시 6개의 숫자가 아닐때")
	@Test
	void errorTest1() {
		customer.setManualLottoNumbers("1,2,3,4,5\n11,12,13,14,15,16");

		assertThatThrownBy(() -> new LottoGeneratorManual().generator(customer))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("6개의 숫자가 아닙니다.");
	}

	@DisplayName("수동로또번호 입력시 중복된 숫자가 있을 때")
	@Test
	void errorTest2() {
		customer.setManualLottoNumbers("1,2,3,4,5,5\n11,12,13,14,15,16");

		assertThatThrownBy(() -> new LottoGeneratorManual().generator(customer))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("숫자는 중복될 수 없습니다.");
	}

	@DisplayName("수동로또번호 입력시 7개의 입력된 숫자 중 중복이 있을 때")
	@Test
	void errorTest3() {
		customer.setManualLottoNumbers("1,2,3,4,5,6,6\n11,12,13,14,15,16");

		assertThatThrownBy(() -> new LottoGeneratorManual().generator(customer))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("6개의 숫자가 아닙니다.");
	}
}
