package lotto.domain.lottomachine;

import java.util.List;

@FunctionalInterface
public interface LottoMachine {

    List<Integer> generate();
}
