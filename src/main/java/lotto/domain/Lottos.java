package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.generator.LottoManualNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;

public class Lottos {

    private final List<Lotto> lottoBunch;

    public Lottos(LottoNumberGenerator lottoNumberGenerator, Piece purchasedLottoCount) {
        lottoBunch = new ArrayList<>();
        for (int i = 0; i < purchasedLottoCount.getPieceNumber(); i++) {
            lottoBunch.add(new Lotto(lottoNumberGenerator.generateNumbers()));
        }
    }
    
    public Lottos mergeLottos(Lottos targetLottos) {
        List<List<Number>> mergedLottoNumbers = new ArrayList<>();
        mergeNumbers(mergedLottoNumbers, this);
        mergeNumbers(mergedLottoNumbers, targetLottos);
        LottoManualNumberGenerator mergedNumberGenerator =
            new LottoManualNumberGenerator(mergedLottoNumbers);
        int rawMergedPiece = mergedLottoNumbers.size();
        Money sumMoney = new Money(rawMergedPiece * Lotto.PRICE);
        return new Lottos(mergedNumberGenerator, new Piece(sumMoney, rawMergedPiece));
    }

    private void mergeNumbers(List<List<Number>> mergedLottoNumbers, Lottos lottos) {
        for (Lotto lotto : lottos.getLottoBunch()) {
            mergedLottoNumbers.add(lotto.getNumbers());
        }
    }

    public EnumMap<LottoRank, Integer> getStatistics(LottoAnnouncement lottoAnnouncement) {
        EnumMap<LottoRank, Integer> getStatistics = setUpStatistics();
        for (Lotto lotto : lottoBunch) {
            LottoRank targetRank = lotto.getLottoRank(lottoAnnouncement);
            getStatistics.replace(targetRank, getStatistics.get(targetRank) + 1);
        }
        return getStatistics;
    }

    private EnumMap<LottoRank, Integer> setUpStatistics() {
        EnumMap<LottoRank, Integer> setUpStatistics = new EnumMap<>(LottoRank.class);
        for (LottoRank singleLottoRank : LottoRank.values()) {
            setUpStatistics.put(singleLottoRank, 0);
        }
        return setUpStatistics;
    }

    public List<Lotto> getLottoBunch() {
        return Collections.unmodifiableList(lottoBunch);
    }

    public int getSize() {
        return lottoBunch.size();
    }
}
