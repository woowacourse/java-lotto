package lotto.domain.lottoTicket;

import lotto.domain.exception.LottoNumberSizeException;
import lotto.domain.exception.OverlapLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottoTest {

    @Test
    void 수동_로또_생성하는_정상_테스트() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        ManualLotto manualLotto = new ManualLotto(inputNumbers);
        assertThat(manualLotto).isEqualTo(new ManualLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 로또가_6개가_아닌_경우_테스트() {
        assertThrows(LottoNumberSizeException.class, () -> {
            List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5);
            new ManualLotto(lottoNumbers);
        });
    }

    @Test
    void 중복된_번호가_있는_경우_테스트() {
        assertThrows(OverlapLottoNumberException.class, () -> {
            List<Integer> lottoNumbers = Arrays.asList(45, 1, 2, 3, 45, 4);
            new ManualLotto(lottoNumbers);
        });
    }
}
