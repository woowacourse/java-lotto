package lotto.model.creator;

import lotto.model.creator.WinnerLottoCreator;
import lotto.model.exception.LottoNumberDuplicationException;
import lotto.model.object.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinnerLottoCreatorTest {
        @Test
        void 당첨_로또_생성_및_중복_검사() {
                List<LottoNumber> lottoNumbers = new ArrayList<LottoNumber>();
                assertThrows(LottoNumberDuplicationException.class, () -> {
                        WinnerLottoCreator.create(new String[]{"1", "2", "3", "4", "5", "1"});
                });
        }
}
