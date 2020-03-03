package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.LottoMachine;
import lotto.domain.lotto.Lotto;
import lotto.domain.number.LottoNumber;

class LottoWinningResultTest {
	private static List<Lotto> lottoTicket;
	private static WinningLotto winningLotto;

	@BeforeAll
	static void setUp() {
		Set<LottoNumber> firstRankLottoNumber = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)
		));
		Set<LottoNumber> missRankLottoNumber = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(12),
			LottoNumber.valueOf(23),
			LottoNumber.valueOf(43),
			LottoNumber.valueOf(14),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(21)
		));
		lottoTicket = new ArrayList<>(Arrays.asList(
			new Lotto(firstRankLottoNumber),
			new Lotto(missRankLottoNumber)));
		winningLotto = new WinningLotto("1,2,3,4,5,6", "10");
	}

	@DisplayName("LottoWinningResult 생성자에 구매한 로또들과 당첨 로또가 입력되었을 때 유효한 값일시 객체 생성 ")
	@Test
	void constructor_ValidRankCount_CreateLottoRankRecode() {
		assertThat(new LottoWinningResult(lottoTicket, winningLotto)).isInstanceOf(LottoWinningResult.class);
	}

	@DisplayName("calculateWinningRatio에 유효한 lottoMachine 객체가 들어오면 수익률 반환")
	@Test
	void calculateWinningRatio_ValidLottoMachine_ReturnWinningRatio() {
		LottoWinningResult lottoWinningResult = new LottoWinningResult(lottoTicket, winningLotto);
		LottoMachine lottoMachine = new LottoMachine("2000", "1");
		assertThat(lottoWinningResult.calculateWinningRatio(lottoMachine)).isEqualTo(100000000);
	}
}
