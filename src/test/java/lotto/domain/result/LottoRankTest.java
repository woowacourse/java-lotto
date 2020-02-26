package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoRankTest {
	@DisplayName("3개의 볼 매칭, 보녀스볼 일치하는 경우 5등 확인")
	@Test
	void getFifthRankTest_true() {
		assertThat(LottoRank.ofValue(3, true)).isEqualTo(LottoRank.FIFTH);
	}

	@DisplayName("3개의 볼 매칭, 보녀스볼 일치하지 않는 경우 5등 확인")
	@Test
	void getFifthRankTest_false() {
		assertThat(LottoRank.ofValue(3, false)).isEqualTo(LottoRank.FIFTH);
	}

	@DisplayName("4개의 볼 매칭, 보녀스볼 일치하는 경우 4등 확인")
	@Test
	void getFourthRankTest_true() {
		assertThat(LottoRank.ofValue(4, true)).isEqualTo(LottoRank.FOURTH);
	}

	@DisplayName("4개의 볼 매칭, 보녀스볼 일치하지 않는 경우 4등 확인")
	@Test
	void getFourthRankTest_false() {
		assertThat(LottoRank.ofValue(4, false)).isEqualTo(LottoRank.FOURTH);
	}

	@DisplayName("5개의 볼 매칭, 보녀스볼 일치하지 않는 경우 3등 확인")
	@Test
	void getThirdRankTest() {
		assertThat(LottoRank.ofValue(5, false)).isEqualTo(LottoRank.THIRD);
	}

	@DisplayName("5개의 볼 매칭, 보녀스볼 일치하는 경우 2등 확인")
	@Test
	void getSecondRankTest() {
		assertThat(LottoRank.ofValue(5, true)).isEqualTo(LottoRank.SECOND);
	}

	@DisplayName("6개의 볼 매칭, 보녀스볼 일치하지 않는 경우 1등 확인")
	@Test
	void getFirstRankTest() {
		assertThat(LottoRank.ofValue(6, false)).isEqualTo(LottoRank.FIRST);
	}

	@DisplayName("1등에 해당하는 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 100})
	void calculateFirstPrize(int count) {
		LottoRank rank = LottoRank.FIRST;
		long expected = 2_000_000_000L * count;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@DisplayName("2등에 해당하는 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 100})
	void calculateSecondPrize(int count) {
		LottoRank rank = LottoRank.SECOND;
		long expected = 30_000_000L * count;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@DisplayName("3등에 해당하는 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 100})
	void calculateThirdPrize(int count) {
		LottoRank rank = LottoRank.THIRD;
		long expected = 1_500_000L * count;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@DisplayName("4등에 해당하는 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 100})
	void calculateFourthPrize(int count) {
		LottoRank rank = LottoRank.FOURTH;
		long expected = 50_000L * count;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@DisplayName("5등에 해당하는 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 100})
	void name(int count) {
		LottoRank rank = LottoRank.FIFTH;
		long expected = 5_000L * count;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}
}
