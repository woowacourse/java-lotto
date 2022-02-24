package lotto.model.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRanks {

    private final List<LottoRank> lottoRanks;

    public LottoRanks(List<LottoRank> lottoRanks) {
        this.lottoRanks = new ArrayList<>(lottoRanks);
    }

    public List<LottoRank> getLottoRanks() {
        return Collections.unmodifiableList(lottoRanks);
    }
}
