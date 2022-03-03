package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generatestrategy.LotteryRandomGeneratorStrategy;

public class LotteriesTest {

	@DisplayName("로또의 개수가 정해진 수 만큼 만들어지는지 확인")
	@ParameterizedTest(name = "{index} {displayName} lotteryNumber={0}")
	@ValueSource(ints = {1, 100, 50})
	void lotteries_number(final int lotteryNumber){
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		final LotteryRandomGeneratorStrategy lotteryGenerator = new LotteryRandomGeneratorStrategy();

		for (int i = 0; i < lotteryNumber; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		final Lotteries lotteries = new Lotteries(lotteriesNumber);

		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteryNumber);
	}
}
