package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {
	private LottoManager lottoManager;

	@BeforeEach
	void setUp() {
		List<Lotto> lotteries = new ArrayList<>();
		lotteries.add(createLotto(1, 7)); // 1, 2, 3, 4, 5, 6
		lotteries.add(createLotto(4, 10)); // 4, 5, 6, 7, 8, 9
		WinLotto winLotto = new WinLotto("1,2,3,4,5,7", "6");
		lottoManager = new LottoManager(lotteries, winLotto);
	}

	@Test
	void compareLotteriesTest() {
		List<Rank> ranks = Arrays.asList(Rank.SECOND, Rank.FIFTH);
		assertThat(lottoManager.compareLotteries()).isEqualTo(ranks);
	}

	Lotto createLotto(int from, int to) {
		List<LottoNo> lottoNos = IntStream.range(from, to)
			.boxed()
			.map(String::valueOf)
			.map(LottoNo::new)
			.collect(Collectors.toList());
		return new Lotto(lottoNos);
	}
}
