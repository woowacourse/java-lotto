package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;
import lottogame.utils.LottoNumbersParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottoGeneratorTest {
    @Test
    void 생성_테스트() {
        assertThat(ManualLottoGenerator.create(LottoNumbersParser
                .parse("1,2,3,4,5,6")))
                .isExactlyInstanceOf(Lotto.class);
    }

    @Test
    void 중복_숫자일_때_예외처리_테스트() {
        assertThrows(InvalidLottoNumberException.class,
                () -> ManualLottoGenerator.create(LottoNumbersParser.parse("1,2,3,4,6,6")));
    }

    @Test
    void 숫자의_개수가_맞지않을_때_예외처리_테스트() {
        assertThrows(InvalidLottoNumberException.class,
                () -> ManualLottoGenerator.create(LottoNumbersParser.parse("1,2,3,4,6")));
    }
}
