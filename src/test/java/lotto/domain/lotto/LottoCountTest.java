package lotto.domain.lotto;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Lotto Count 테스트
 *
 * @author 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/26
 */
public class LottoCountTest {
	@Test
	void LottoCount_생성자_올바른_동작_확인() {
		assertThat(new LottoCount(14, "3")).isInstanceOf(LottoCount.class);
	}

	@ParameterizedTest
	@NullSource
	void LottoCount_생성자_null_입력시_예외처리(String nullString) {
		assertThatNullPointerException().isThrownBy(() -> {
			new LottoCount(14, nullString);
		});
	}

	@ParameterizedTest
	@EmptySource
	void LottoCount_생성자_빈_문자열_입력시_예외처리(String emptyString) {
		assertThatThrownBy(() -> {
			new LottoCount(14, emptyString);
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("입력금액이 정수가 아닙니다.");
	}

	@Test
	void LottoCount_생성자_정수_이외의_입력시_예외처리() {
		assertThatThrownBy(() -> {
			new LottoCount(14, "lotto");
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("입력금액이 정수가 아닙니다.");
	}

	@Test
	void LottoCount_생성자_수동_장수가_양의_정수가_아닐시_예외처리() {
		assertThatThrownBy(() -> {
			new LottoCount(14, "-3");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("수동 구매 장수는 0장에서 총 로또 구매 장수 사이여야합니다.");
	}

	@Test
	void LottoCount_생성자_수동_장수가_총_장수보다_많을시_예외처리() {
		assertThatThrownBy(() -> {
			new LottoCount(14, "25");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("수동 구매 장수는 0장에서 총 로또 구매 장수 사이여야합니다.");
	}
}
