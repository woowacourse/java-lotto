package lotto.domain.lottomachine;

import lotto.domain.primitive.LottoNumber;

import java.util.List;

@FunctionalInterface
public interface LottoMachine {

    List<LottoNumber> generate();
}
