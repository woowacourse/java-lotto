package lotto.domain.generator;

import java.util.List;
import lotto.domain.vo.LottoNumber;

@FunctionalInterface
public interface LottoTicketGenerator {
    List<LottoNumber> generate();
}
