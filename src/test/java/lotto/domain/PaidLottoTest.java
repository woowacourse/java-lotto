package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class PaidLottoTest {
	private WinningLotto winningLotto;
	private PaidLotto paidLotto;
	private BonusLottoNumber bonusLottoNumber;

	@BeforeEach
	void setUp() {
		winningLotto = new WinningLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(6)
				)
		));

		bonusLottoNumber = new BonusLottoNumber("7", winningLotto);
	}

	@Test
	void getRank_올바른_1등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList( // TODO 이부분들 뺄 방법이 있을까?
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(6)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FIRST);
	}

	@Test
	void getRank_올바른_2등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(7)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.SECOND);
	}

	@Test
	void getRank_올바른_3등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(8)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.THIRD);
	}

	@Test
	void getRank_올바른_4등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(8),
						LottoNumber.of(9)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FOURTH);
	}

	@Test
	void getRank_올바른_5등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(8),
						LottoNumber.of(9),
						LottoNumber.of(7)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FIFTH);
	}

	@Test
	void getRank_올바른_6등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(8),
						LottoNumber.of(9),
						LottoNumber.of(10),
						LottoNumber.of(11)
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.SIXTH);
	}

	@Test
	void calculateMatchCount_올바른_동작_확인() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(8),
						LottoNumber.of(10),
						LottoNumber.of(44)
				)
		);

		PaidLotto paidLotto = new PaidLotto(lottoNumbers);

		assertThat(paidLotto.calculateMatchCount(winningLotto)).isEqualTo(3);
	}

	@ParameterizedTest
	@NullSource
	void calculateMatchCount_매개변수_null_예외처리(WinningLotto nullLotto) {
		List<LottoNumber> lottoNumbers = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(8),
						LottoNumber.of(10),
						LottoNumber.of(44)
				)
		);

		paidLotto = new PaidLotto(lottoNumbers);

		assertThatThrownBy(() -> {
			paidLotto.calculateMatchCount(nullLotto);
		}).isInstanceOf(NullPointerException.class)
				.hasMessage("매개변수가 null 입니다.");
	}
}
