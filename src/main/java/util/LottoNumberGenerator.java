package util;

import java.util.List;

public interface LottoNumberGenerator {

    int LOTTO_NUMBER_LENGTH = 6;

    List<Integer> generate();
}
