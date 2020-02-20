package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> create();
}
