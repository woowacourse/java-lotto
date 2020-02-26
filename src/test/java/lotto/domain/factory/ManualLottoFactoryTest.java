package lotto.domain.factory;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;

public class ManualLottoFactoryTest {

	@Test
	void createTest() {
		Lotto lotto = new Lotto(Arrays.stream(new String[]{"1", "2", "3", "4", "5", "6"})
			.map(LottoNo::new)
			.collect(Collectors.toList()));
		assertThat(new ManualLottoFactory("1, 2, 3, 4, 5, 6").create()).isEqualTo(lotto);
	}
}
