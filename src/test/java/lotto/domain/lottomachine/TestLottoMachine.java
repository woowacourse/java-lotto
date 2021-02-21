package lotto.domain.lottomachine;

import lotto.domain.primitive.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestLottoMachine implements LottoMachine {
    @Override
    public List<LottoNumber> generate() {
        return IntStream.rangeClosed(1, 6)
                        .boxed()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());
    }
}
