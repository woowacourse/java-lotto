package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTicketTest {

	@DisplayName("로또 중복 생성 불가 테스트")
	@Test
	void makeLottoDuplication() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(5));
		assertThatThrownBy(() -> new LottoTicket(balls)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@DisplayName("로또 여섯개 볼 안받았을 경우 예외 테스트")
	@Test
	void makeLottoCountNotSix() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5));
		assertThatThrownBy(() -> new LottoTicket(balls)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 볼의 갯수가 적절하지 않습니다.");
	}

	@DisplayName("보내준 볼을 로또에서 가지고 있는지 확인")
	@ParameterizedTest
	@CsvSource(value = {"1,true", "19,false"})
	void hasBall(int ballNo, boolean expected) {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
		LottoTicket lottoTicket = new LottoTicket(balls);
		assertThat(lottoTicket.contains(Ball.of(ballNo))).isEqualTo(expected);
	}

	@DisplayName("보내준 로또와 얼마나 일치하는지 확인")
	@Test
	void hasLottoBall() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
		LottoTicket lottoTicket = new LottoTicket(balls);
		List<Ball> balls2 = Arrays.asList(Ball.of(1), Ball.of(12), Ball.of(7), Ball.of(4), Ball.of(5), Ball.of(6));
		LottoTicket lottoTicket2 = new LottoTicket(balls2);
		assertThat(lottoTicket.findMatchBallCount(lottoTicket2)).isEqualTo(4);
	}

	@Test
	void constructByRawNumber() {
		LottoTicket lottoTicket = LottoTicket.of("1, 2, 3, 4, 5, 6");
		assertThat(lottoTicket.contains(Ball.of(1)));
		assertThat(lottoTicket.contains(Ball.of(2)));
		assertThat(lottoTicket.contains(Ball.of(3)));
		assertThat(lottoTicket.contains(Ball.of(4)));
		assertThat(lottoTicket.contains(Ball.of(5)));
		assertThat(lottoTicket.contains(Ball.of(6)));
	}
}
