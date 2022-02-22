package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.LottoCount;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class lottoTest {
    @Test
    public void 구입한_로또_개수_테스트() {
        int result = new LottoCount().getLottoCount(14000);
        assertThat(result).isEqualTo(14);
    }
}
