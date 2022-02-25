package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {

	@DisplayName("총 당첨금 확인")
	@Test
	void count_ranks() {
		//given
		Map<Rank, Integer> ranks = Rank.getMap();
		ranks.replace(Rank.FIFTH, 4);
		//when
		Payment payment = new Payment(20000);
		LottoResult result = new LottoResult(ranks);
		//then
		assertThat(result.calculateProfitRate(payment)).isEqualTo(1.0);
	}

	@DisplayName("생성값이 null 일 경우")
	@Test
	void make_null() {
		assertThatThrownBy(() -> new LottoResult(null))
			.isInstanceOf(NullPointerException.class)
			.hasMessage(null);
	}
}