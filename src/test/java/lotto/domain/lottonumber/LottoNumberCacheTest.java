package lotto.domain.lottonumber;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberCacheTest {
	@DisplayName("캐싱된 값을 제대로 얻어오는 지 확인")
	@Test
	void createNumber_existingNumber_returnNumber() {
		assertThat(LottoNumberCache.getNumber(3)).isEqualTo(new LottoNumber(3));
	}
}
