package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoNumberException;
import lottogame.utils.LottoNumbersParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoGeneratorTest {
    @Test
    void 생성_테스트() {
        assertThat(LottoGenerator.create("1,2,3,4,5,6")).isExactlyInstanceOf(Lotto.class);
    }
}
