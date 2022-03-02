package util;

import domain.LottoNumber;
import java.util.List;

public interface LottoStrategy {

    List<LottoNumber> generateAutoNumbers();
}
