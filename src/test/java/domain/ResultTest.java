package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
	@Test
	@DisplayName("올바른 수익률을 산출하였는지 확인")
	void correctProfitRate() {
		Result result = new Result(List.of(ResultStatics.values()), 10000);
		float profitRate = (float) Arrays.stream(ResultStatics.values()).mapToInt(ResultStatics::getPrice).sum() / 10000;

		assertThat(result.getProfitRate()).isEqualTo(profitRate);
	}
}
