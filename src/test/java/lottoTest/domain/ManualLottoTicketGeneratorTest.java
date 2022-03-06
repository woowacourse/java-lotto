package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicketsManualGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ManualLottoTicketGeneratorTest {

    @Test
    void 수동_로또_티켓을_생성하는_기능() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(LottoTicketsManualGenerator.createLottoNumbers(input).get(0).getNumbers())
                .containsExactly(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
    }
}