package lottogame.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<Lotto> lottos = new ArrayList<>();

    public static LottoTickets generate() {
        return new LottoTickets();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void addManualLotto(String manualLotto) {
        Lotto lotto = ManualLottoGenerator.create(manualLotto);
        lottos.add(lotto);
    }

    public void createAutoLottos() {
        lottos.add(AutoLottoGenerator.create());
    }

    public boolean isPossibleCreateLottoNumberOf(int numberOfTicket) {
        return lottos.size() < numberOfTicket;
    }

    public int numberOfLottos() {
        return lottos.size();
    }

    List<Rank> getMatchResultEachLotto(WinningLotto winningLotto) {
        List<Rank> matchResults = new ArrayList<>();

        for (Lotto lotto : lottos) {
            matchResults.add(lotto.getMatchResult(winningLotto));
        }

        return matchResults;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto).append("\n");
        }
        return stringBuilder.toString();
    }
}
