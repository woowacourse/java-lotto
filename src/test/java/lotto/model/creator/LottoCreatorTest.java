package lotto.model.creator;

import lotto.model.exception.LottoNumberDuplicationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoCreatorTest {

        @Test
        void 로또_생성_및_중복_검사() {
                assertThrows(LottoNumberDuplicationException.class, () -> {
                        LottoCreator.create(new String[]{"1", "2", "3", "4", "5", "1"});
                });
        }
}
