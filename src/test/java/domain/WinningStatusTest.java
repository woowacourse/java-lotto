package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class WinningStatusTest {

	@Test
	void winningStatusFive() {
		WinningStatus winningStatus = WinningStatus.of(5, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.FIVE);
	}

	@Test
	void winningStatusFiveWithBonus() {
		WinningStatus winningStatus = WinningStatus.of(5, true);
		assertThat(winningStatus).isEqualTo(WinningStatus.FIVE_AND_BONUS);
	}

	@Test
	void winningStatusSix() {
		WinningStatus winningStatus = WinningStatus.of(6, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.SIX);
	}

	@Test
	void winningStatusFour() {
		WinningStatus winningStatus = WinningStatus.of(4, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.FOUR);
	}

	@Test
	void winningStatusThree() {
		WinningStatus winningStatus = WinningStatus.of(3, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.THREE);
	}

	@Test
	void winningStatusTwo() {
		WinningStatus winningStatus = WinningStatus.of(2, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.NOTHING);
	}

	@Test
	void winningStatusOne() {
		WinningStatus winningStatus = WinningStatus.of(1, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.NOTHING);
	}

	@Test
	void winningStatusZero() {
		WinningStatus winningStatus = WinningStatus.of(0, false);
		assertThat(winningStatus).isEqualTo(WinningStatus.NOTHING);
	}
}
