package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankTest {

	@Test
	void getFifthRankTest_true() {
		assertThat(LottoRank.findRank(3, true)).isEqualTo(LottoRank.FIFTH);
	}

	@Test
	void getFifthRankTest_false() {
		assertThat(LottoRank.findRank(3, false)).isEqualTo(LottoRank.FIFTH);
	}

	@Test
	void getFourthRankTest_true() {
		assertThat(LottoRank.findRank(4, true)).isEqualTo(LottoRank.FOURTH);
	}

	@Test
	void getFourthRankTest_false() {
		assertThat(LottoRank.findRank(4, false)).isEqualTo(LottoRank.FOURTH);
	}

	@Test
	void getThirdRankTest() {
		assertThat(LottoRank.findRank(5, false)).isEqualTo(LottoRank.THIRD);
	}

	@Test
	void getSecondRankTest() {
		assertThat(LottoRank.findRank(5, true)).isEqualTo(LottoRank.SECOND);
	}

	@Test
	void getFirstRankTest() {
		assertThat(LottoRank.findRank(6, false)).isEqualTo(LottoRank.FIRST);
	}

	@ParameterizedTest
	@CsvSource(value = {"-1,false", "0,false", "2,false", "4,true", "6,true", "7,false"})
	void validMatchCountTest(int matchCount, boolean expected) {
		assertThat(LottoRank.isValidMatchCount(matchCount)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2000000000", "2,4000000000", "3,6000000000", "4,8000000000"})
	void calculateFirstPrize(int count, long expected) {
		LottoRank rank = LottoRank.FIRST;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,30000000", "2,60000000", "3,90000000", "4,120000000"})
	void calculateSecondPrize(int count, long expected) {
		LottoRank rank = LottoRank.SECOND;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,1500000", "2,3000000", "3,4500000", "4,6000000"})
	void calculateThirdPrize(int count, long expected) {
		LottoRank rank = LottoRank.THIRD;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,50000", "2,100000", "3,150000", "4,200000"})
	void calculateFourthPrize(int count, long expected) {
		LottoRank rank = LottoRank.FOURTH;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,5000", "2,10000", "3,15000", "4,20000"})
	void name(int count, long expected) {
		LottoRank rank = LottoRank.FIFTH;
		assertThat(rank.calculateTotalMoney(count)).extracting("money").isEqualTo(expected);
	}
}
