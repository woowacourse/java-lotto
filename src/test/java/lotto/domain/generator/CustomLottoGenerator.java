package lotto.domain.generator;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class CustomLottoGenerator implements Generator {

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(LottoNumber.valueOf(i));
        }
        return new Lotto(numbers);
    }
}
