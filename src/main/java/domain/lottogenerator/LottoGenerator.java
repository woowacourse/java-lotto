package domain.lottogenerator;

import domain.LottoNumber;
import java.util.List;

public interface LottoGenerator {
    List<LottoNumber> generate();
}
