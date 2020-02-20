package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.FOUR,
						LottoNumber.FIVE,
						LottoNumber.SIX
				)
		));

		bonusLottoNumber = new BonusLottoNumber(7, winningLotto);
	}

	@Test
	void getRank_올바른_1등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.FOUR,
						LottoNumber.FIVE,
						LottoNumber.SIX
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FIRST);
	}

	@Test
	void getRank_올바른_2등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.FOUR,
						LottoNumber.FIVE,
						LottoNumber.SEVEN
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.SECOND);
	}

	@Test
	void getRank_올바른_3등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.FOUR,
						LottoNumber.FIVE,
						LottoNumber.EIGHT
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.THIRD);
	}

	@Test
	void getRank_올바른_4등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.FOUR,
						LottoNumber.EIGHT,
						LottoNumber.NINE
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FOURTH);
	}

	@Test
	void getRank_올바른_5등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.EIGHT,
						LottoNumber.NINE,
						LottoNumber.SEVEN
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.FIFTH);
	}

	@Test
	void getRank_올바른_6등_확인() {
		paidLotto = new PaidLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.EIGHT,
						LottoNumber.NINE,
						LottoNumber.TEN,
						LottoNumber.ELEVEN
				)
		));

		Rank lottoRank = paidLotto.getRank(winningLotto, bonusLottoNumber);
		assertThat(lottoRank).isEqualTo(Rank.SIXTH);
	}
}
