package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.receiver.LottoReceiver;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lotto;

    private Lotto(List<LottoNumber> lotto) {
        this.lotto = Collections.unmodifiableList(lotto);
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers(LOTTO_SIZE));
    }

    public static Lotto generateLottoByManual(String input) {
        return new Lotto(LottoReceiver.receive(input));
    }

    public Rank getRank(WinningNumbers winningNumbers) {
        int winningNumberMatchCount = winningNumbers.getWinningLottoMatchCount(lotto);
        boolean bonusNumberMatch = winningNumbers.isBonusNumberContainedAt(lotto);
        return Rank.getRank(winningNumberMatchCount, bonusNumberMatch);
    }

    public int getMatchCount(List<LottoNumber> lottoToCompare) {
        return (int) lottoToCompare.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumberToCompare) {
        return lotto.contains(lottoNumberToCompare);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
