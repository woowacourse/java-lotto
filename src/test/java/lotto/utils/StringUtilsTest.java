package lotto.utils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;

public class StringUtilsTest {

	@Test
	void split() {
		List<LottoNo> numbers = IntStream.range(1, 7)
			.boxed()
			.map(LottoNo::new)
			.collect(Collectors.toList());
		Lotto lotto = new Lotto(numbers);
		Lotto list = new Lotto(StringUtils.split("1,2,3,4,5,6"));
		assertThat(list).isEqualTo(lotto);
	}
}
