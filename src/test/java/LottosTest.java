import static org.assertj.core.api.Assertions.assertThat;

import domain.Lottos;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@Test
	void generateCorrectCountOfLotto() {
		Lottos lottos = new Lottos(12000);
		assertThat(lottos.getLottosSize()).isEqualTo(12);
	}
}
