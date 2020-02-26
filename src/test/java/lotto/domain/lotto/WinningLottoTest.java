package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.Rank;
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
 * WinningLotto 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLottoTest {
	private WinningLotto winningLotto;
	private Lotto PurchasedLotto;

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
		),
				LottoNumber.of(7));
	}

	@Test
	void getRank_올바른_1등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(6)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.FIRST);
	}

	@Test
	void getRank_올바른_2등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(7)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.SECOND);
	}

	@Test
	void getRank_올바른_3등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(8)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.THIRD);
	}

	@Test
	void getRank_올바른_4등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(8),
						LottoNumber.of(9)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.FOURTH);
	}

	@Test
	void getRank_올바른_5등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(8),
						LottoNumber.of(9),
						LottoNumber.of(7)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.FIFTH);
	}

	@Test
	void getRank_올바른_6등_확인() {
		PurchasedLotto = new Lotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(8),
						LottoNumber.of(9),
						LottoNumber.of(10),
						LottoNumber.of(11)
				)
		));
		Rank lottoRank = winningLotto.getRank(PurchasedLotto);

		assertThat(lottoRank).isEqualTo(Rank.SIXTH);
	}

	@Test
	void calculateMatchCount_서로_겹치는_개수_반환() {
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
		Lotto PurchasedLotto = new Lotto(lottoNumbers);

		assertThat(winningLotto.calculateMatchCount(PurchasedLotto)).isEqualTo(3);
	}

	@ParameterizedTest
	@NullSource
	void calculateMatchCount_매개변수_null_예외처리(Lotto nullLotto) {
		assertThatThrownBy(() -> {
			winningLotto.calculateMatchCount(nullLotto);
		}).isInstanceOf(NullPointerException.class)
				.hasMessage("매개변수가 null 입니다.");
	}

	@Test
	void isContain_주어진_로또번호_포함시_true_반환() {
		assertThat(
				winningLotto.isContain(LottoNumber.of(1))
		).isTrue();
	}
}
