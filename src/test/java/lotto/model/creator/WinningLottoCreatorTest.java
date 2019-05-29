package lotto.model.creator;

import lotto.model.exception.LottoNumberDuplicationException;
import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoCreatorTest {
        @Test
        void 당첨_로또_생성_및_중복_검사() {
                List<LottoNumber> lottoNumbers = new ArrayList<LottoNumber>();
                assertThrows(LottoNumberDuplicationException.class, () -> {
                        WinningLottoCreator.create(new String[]{"1", "2", "3", "4", "5", "1"});
                });
        }

        @Test
        void 당첨_로또_생성_검사() {
                List<LottoNumber> lottoNumbers = new ArrayList<LottoNumber>();
                Lotto winningLotto = WinningLottoCreator.create(new String[]{"1", "2", "3", "4", "5", "6"});

                assertThat(winningLotto.getLottoNumbers().get(0)).isEqualTo(LottoNumberCreator.create(1));
                assertThat(winningLotto.getLottoNumbers().get(1)).isEqualTo(LottoNumberCreator.create(2));
                assertThat(winningLotto.getLottoNumbers().get(2)).isEqualTo(LottoNumberCreator.create(3));
                assertThat(winningLotto.getLottoNumbers().get(3)).isEqualTo(LottoNumberCreator.create(4));
                assertThat(winningLotto.getLottoNumbers().get(4)).isEqualTo(LottoNumberCreator.create(5));
                assertThat(winningLotto.getLottoNumbers().get(5)).isEqualTo(LottoNumberCreator.create(6));
        }
}
