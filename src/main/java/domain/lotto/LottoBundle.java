package domain.lotto;

import domain.result.LottoRank;
import domain.result.LottoResult;
import domain.result.WinningResult;

import java.util.*;

public class LottoBundle {
    private final List<Lotto> manualLottoBundle;
    private final List<Lotto> autoLottoBundle;

    public LottoBundle(final List<Lotto> manualLottoBundle, final List<Lotto> autoLottoBundle) {
        this.manualLottoBundle = new ArrayList<>(manualLottoBundle);
        this.autoLottoBundle = new ArrayList<>(autoLottoBundle);
    }

    public LottoBundle(final List<Lotto> autoLottoBundle) {
        this(new ArrayList<>(), autoLottoBundle);
    }

    public LottoResult checkResult(final WinningResult winningResult) {
        final Map<LottoRank, Integer> lottoResult = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoResult.put(lottoRank, 0));

        for (Lotto lotto : autoLottoBundle) {
            LottoRank lottoRank = checkSingleLottoRank(lotto, winningResult);
            lottoResult.put(lottoRank, lottoResult.get(lottoRank) + 1);
        }

        return new LottoResult(lottoResult);
    }

    private LottoRank checkSingleLottoRank(final Lotto lotto, final WinningResult winningResult) {
        final int matchNumberResult = winningResult.matchWinningLotto(lotto);
        final boolean matchBonusBallResult = winningResult.matchBonusBall(lotto);
        final LottoRank lottoRank = LottoRank.findLottoRank(matchNumberResult, matchBonusBallResult);
        return lottoRank;
    }

    public int countNumberOfLotto() {
        return manualLottoBundle.size() + autoLottoBundle.size();
    }

    public int countNumberOfManualLotto() {
        return manualLottoBundle.size();
    }

    public int countNumberOfAutoLotto() {
        return autoLottoBundle.size();
    }

    public List<Lotto> getLottoBundle() {
        List<Lotto> lottoBundle = new ArrayList<>();
        lottoBundle.addAll(manualLottoBundle);
        lottoBundle.addAll(autoLottoBundle);
        return Collections.unmodifiableList(lottoBundle);
    }
}
