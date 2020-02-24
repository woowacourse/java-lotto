package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.LottoNumber;

class LottoRankRecordTest {
	private static Lottos lottos;
	private static WinningLotto winningLotto;

	@BeforeAll
	static void setUp() {
		Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
				LottoNumber.valueOf(1),
				LottoNumber.valueOf(2),
				LottoNumber.valueOf(3),
				LottoNumber.valueOf(4),
				LottoNumber.valueOf(5),
				LottoNumber.valueOf(6)
		));
		Set<LottoNumber> lottoNumbers2 = new HashSet<>(Arrays.asList(
				LottoNumber.valueOf(12),
				LottoNumber.valueOf(23),
				LottoNumber.valueOf(43),
				LottoNumber.valueOf(14),
				LottoNumber.valueOf(5),
				LottoNumber.valueOf(21)
		));

		Set<LottoNumber> lottoNumbers3 = new HashSet<>(Arrays.asList(
				LottoNumber.valueOf(1),
				LottoNumber.valueOf(2),
				LottoNumber.valueOf(3),
				LottoNumber.valueOf(4),
				LottoNumber.valueOf(5),
				LottoNumber.valueOf(6)
		));
		lottos = new Lottos(Arrays.asList(
				new Lotto(lottoNumbers),
				new Lotto(lottoNumbers2)));
		Lotto lotto = new Lotto(lottoNumbers3);
		LottoNumber bonusNumber = LottoNumber.valueOf(10);
		winningLotto = new WinningLotto(lotto, bonusNumber);
	}

	@DisplayName("LottoRankRecord 생성자에 구매한 로또들과 당첨 로또가 입력되었을 때 유효한 값일시 객체 생성 ")
	@Test
	void constructor_validRankCount_createLottoRankRecode() {
		assertThat(new LottoRankRecord(lottos, winningLotto)).isInstanceOf(LottoRankRecord.class);
	}
}
