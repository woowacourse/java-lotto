package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

	private List<LottoNumber> lottos = new ArrayList<>();

	@BeforeEach
	void setUp() {
		lottos.add(new LottoNumber(1));
		lottos.add(new LottoNumber(2));
		lottos.add(new LottoNumber(3));
		lottos.add(new LottoNumber(4));
		lottos.add(new LottoNumber(5));
	}

	@DisplayName("개수 성공")
	@Test
	void lotto_size_success() {
		lottos.add(new LottoNumber(6));
		assertThatCode(() -> new Lotto(lottos))
			.doesNotThrowAnyException();
	}

	@DisplayName("개수 실패")
	@Test
	void lotto_size_fail() {
		assertThatThrownBy(() -> new Lotto(lottos))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("중복 금지")
	@Test
	void duplicate_fail() {
		lottos.add(new LottoNumber(5));
		assertThatThrownBy(() -> new Lotto(lottos))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("매칭 숫자 계산")
	@Test
	void calculate_match_count() {
		//given
		lottos.add(new LottoNumber(6));
		Lotto lotto = new Lotto(lottos);
		Lotto targetLotto = Lotto.of(new String[]{"7", "5", "4", "3", "2", "1"});

		//when
		int count = lotto.calculateMatchCount(targetLotto);

		//then
		assertThat(count).isEqualTo(5);
	}

	@AfterEach
	void clear() {
		lottos.clear();
	}
}
