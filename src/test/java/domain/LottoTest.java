package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

	private List<LottoNumber> lottos;

	@BeforeEach
	void setUp() {
		lottos = new ArrayList<>();

		for (int i = 1; i < 6; i++) {
			lottos.add(LottoNumber.of(i));
		}
	}

	@DisplayName("개수 성공")
	@Test
	void lotto_size_success() {
		lottos.add(LottoNumber.of(6));
		assertThatCode(() -> new Lotto(lottos)).doesNotThrowAnyException();
	}

	@DisplayName("개수 실패")
	@Test
	void lotto_size_fail() {
		assertThatThrownBy(() -> new Lotto(lottos)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("중복 금지")
	@Test
	void duplicate_fail() {
		lottos.add(LottoNumber.of(5));
		assertThatThrownBy(() -> new Lotto(lottos)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("매칭 숫자 계산")
	@Test
	void calculate_match_count() {
		//given
		lottos.add(LottoNumber.of(6));
		Lotto lotto = new Lotto(lottos);
		Lotto targetLotto = Lotto.of(new String[] {"7", "5", "4", "3", "2", "1"});

		//when
		int count = lotto.calculateMatchCount(targetLotto);

		//then
		assertThat(count).isEqualTo(5);
	}
}
