package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 로또_번호_갯수_6개인지_확인(){
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<List<String>> manualLottoNumbers = new ArrayList<>();
        List<String> manualLottoNumber = Arrays.asList("1","2","3","4","5","6");
        manualLottoNumbers.add(manualLottoNumber);
        LottoTickets lotto = LottoFactory.createLottoTickets(new LottoCount(2,1), randomNumberGenerator, manualLottoNumbers);
        assertThat(lotto.getLottoTickets().get(0).getSize()).isEqualTo(6);
        assertThat(lotto.getLottoTickets().get(1).getSize()).isEqualTo(6);

        assertThatThrownBy(() -> {
            Lotto.checkLottoSizeSix(7);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }
}
