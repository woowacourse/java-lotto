package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumOfLotteryTest {

	@Test
	@DisplayName("수동 로또 입력 개수가 총 구매한 로또 개수보다 많을 경우 에러 발생")
	void test_manual_lottery_validation() {
		//given
		final int numOfTotalLottery = 10;
		final int numOfManualLottery = 11;
		//when
		//then
		assertThatThrownBy(() -> NumOfLottery.of(numOfTotalLottery, numOfManualLottery))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수동 로또의 개수가 구입한 로또 총 개수보다 많을 수 없습니다.");
	}
}