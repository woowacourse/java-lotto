package lotto.utils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;

public class LottoNoUtilsTest {

	@DisplayName("split하고 LottoNo객체를 잘 생성하는 지 확인")
	@Test
	void split() {
		List<LottoNo> numbers = IntStream.range(1, 7)
			.boxed()
			.map(String::valueOf)
			.map(LottoNo::new)
			.collect(Collectors.toList());
		Lotto excepts = new Lotto(numbers);
		Lotto lotto = new Lotto(LottoNoUtils.split("1,2,3,4,5,6"));
		assertThat(lotto).isEqualTo(excepts);
	}
}
