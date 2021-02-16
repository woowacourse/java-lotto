package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List lottoNumbers;

    public Lotto(List lottoNumbers) {
        this.lottoNumbers = new ArrayList(lottoNumbers);
    }
}
