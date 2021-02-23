package lotto.view;

import lotto.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OutputViewTest {
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outputStream));
	}

	@DisplayName("구매한 로또들을 제대로 출력하는지")
	@Test
	void printLottoPurchaseSummaryBasedOn() {
		OutputView.printLottoPurchaseSummaryBasedOn(new Lottos(Arrays.asList(
				createCustomLotto(1, 2, 3, 4, 5, 6),
				createCustomLotto(1, 2, 3, 4, 5, 7),
				createCustomLotto(1, 2, 3, 4, 5, 8)
		)), new LottoMachine(new Money(3000), new LottoQuantity(2)));


		StringBuilder sb = new StringBuilder();
		sb.append("수동으로 2장, 자동으로 1개를 구매했습니다.\n");
		sb.append("[1, 2, 3, 4, 5, 6]\n");
		sb.append("[1, 2, 3, 4, 5, 7]\n");
		sb.append("[1, 2, 3, 4, 5, 8]\n\n");
		String expectedLottoPurchaseSummaryOutput = sb.toString();

		assertThat(outputStream.toString()).isEqualTo(expectedLottoPurchaseSummaryOutput);
	}

	@DisplayName("당첨 통계를 올바로 출력 하는지")
	@Test
	void printResultStatisticsBasedOn() {
		OutputView.printResultStatisticsBasedOn(new LottoStatistics(Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD), new Money(3000)));

		StringBuilder sb = new StringBuilder();
		sb.append("당첨 통계\n");
		sb.append("---------\n");
		sb.append("3개 일치 (5000원)- 0개\n");
		sb.append("4개 일치 (50000원)- 0개\n");
		sb.append("5개 일치 (1500000원)- 1개\n");
		sb.append("5개 일치, 보너스 볼 일치(30000000원)- 1개\n");
		sb.append("6개 일치 (2000000000원)- 1개\n");
		sb.append(String.format("총 수익률은 %.2f입니다.\n", ((float) Rank.FIRST.getPrize() + Rank.SECOND.getPrize() + Rank.THIRD.getPrize()) / 3000));

		String expectedResultStatisticsOutput = sb.toString();

		assertThat(outputStream.toString()).isEqualTo(expectedResultStatisticsOutput);
	}

	private Lotto createCustomLotto(final int... numbers) {
		return new Lotto(Arrays.asList(
				new LottoNumber(numbers[0]),
				new LottoNumber(numbers[1]),
				new LottoNumber(numbers[2]),
				new LottoNumber(numbers[3]),
				new LottoNumber(numbers[4]),
				new LottoNumber(numbers[5])));
	}

	@AfterEach
	void tearDown() {
		System.setOut(System.out);
		outputStream.reset();
	}
}