package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<Lotto> lottos = new ArrayList<>();

    public static LottoTickets generate() {
        return new LottoTickets();
    }

    public void addManualLotto(String manualLotto) {
        Lotto lotto = LottoGenerator.create(manualLotto);
        if(lotto != null){
            lottos.add(lotto);
        }
    }

    public void createAutoLottos() {
        lottos.add(LottoGenerator.create());
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
