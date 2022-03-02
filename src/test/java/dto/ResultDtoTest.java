package dto;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ResultDtoTest {
    @Test
    void create_test() {

        final List<Lotto> lottos = LottoFactory.generateAutoLottoGroup(2);
        final ResultDto resultDto = new ResultDto(lottos, Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6")),
            new LottoNumber(5));

        System.out.println(resultDto);
    }
}
