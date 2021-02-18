package lotto.domain.ticketFactory;

import java.util.Set;
import lotto.domain.LottoNumber;

public interface NumbersGenerator {
    Set<LottoNumber> generateNumbers();

}
