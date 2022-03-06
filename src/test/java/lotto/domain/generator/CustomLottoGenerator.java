package lotto.domain.generator;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class CustomLottoGenerator implements Generator {

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = new ArrayList<>(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        return new Lotto(numbers);
    }
}
