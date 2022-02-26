package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.receiver.LottoReceiver;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers());
    }

    public static Lotto generateLottoByManual(String input) {
        return new Lotto(LottoReceiver.receive(input));
    }

    public Rank getRank(WinningLotto winningLotto, BonusNumber bonusNumber) {
        int matchCount = getMatchCount(winningLotto.getWinningLotto());
        boolean contains = isContain(bonusNumber.getBonusNumber());
        return Rank.getRank(matchCount, contains);
    }

    private int getMatchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto.lottoNumbers::contains)
                .count();
    }

    public boolean isContain(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
