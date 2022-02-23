package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

	@Test
	void 개수_성공() {
		lottos.add(new LottoNumber(6));
		assertThatCode(() -> new Lotto(lottos))
			.doesNotThrowAnyException();
	}

	@Test
	void 개수_실패() {
		assertThatThrownBy(() -> new Lotto(lottos))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 중복_불가_성공() {
		lottos.add(new LottoNumber(5));
		assertThatThrownBy(() -> new Lotto(lottos))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 매칭_숫자_계산() {
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
