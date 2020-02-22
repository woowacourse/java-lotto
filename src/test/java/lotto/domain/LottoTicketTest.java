package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.util.StringUtil;

public class LottoTicketTest {

	@DisplayName("로또 중복 생성 불가 테스트")
	@Test
	void makeLottoDuplication() {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(5));
		assertThatThrownBy(() -> new LottoTicket(balls)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@DisplayName("로또 여섯개 볼 안받았을 경우 예외 테스트")
	@Test
	void makeLottoCountNotSix() {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5));
		assertThatThrownBy(() -> new LottoTicket(balls)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 볼의 갯수가 적절하지 않습니다.");
	}

	@DisplayName("보내준 볼을 로또에서 가지고 있는지 확인")
	@ParameterizedTest
	@CsvSource(value = {"1,true", "19,false"})
	void hasBall(int ballNo, boolean expected) {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
		LottoTicket lottoTicket = new LottoTicket(balls);
		assertThat(lottoTicket.contains(Ball.valueOf(ballNo))).isEqualTo(expected);
	}

	@DisplayName("보내준 로또와 얼마나 일치하는지 확인")
	@Test
	void hasLottoBall() {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
		LottoTicket lottoTicket = new LottoTicket(balls);
		List<Ball> balls2 = Arrays.asList(Ball.valueOf(1), Ball.valueOf(12), Ball.valueOf(7), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
		LottoTicket lottoTicket2 = new LottoTicket(balls2);
		assertThat(lottoTicket.findMatchingBall(lottoTicket2)).isEqualTo(4);
	}

	@DisplayName("부적합한 번호 문자열 입력시 로또 발급 실패")
	@ParameterizedTest
	@ValueSource(strings = {"1, 2, 3, 4, 5, ", "1, 2, 3, 4, 5, 6.", "1, 2, 3, 4, 5"})
	void constructFailByRawNumber(String rawNumbers) {
		assertThatThrownBy(() -> LottoTicket.of(StringUtil.splitRawLottoNumbers(rawNumbers)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("문자열 목록으 로또 발급 기능 테스트")
	@Test
	void constructByStrings() {
		LottoTicket lottoTicket = LottoTicket.of("1", "2", "3", "4", "5", "6");
		List<Ball> balls = lottoTicket.getBalls();
		assertThat(balls).containsExactly(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3), Ball.valueOf(4), Ball.valueOf(5), Ball.valueOf(6));
	}
}
