package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<Lotto> lottos = new ArrayList<>();
    private final int numberOfManualTicket;

    public LottoTickets(int numberOfManualTicket, Money money) {
        if (!money.isValidateAmount(numberOfManualTicket)) {
            throw new InvalidLottoNumberException("수동 구매 수는 " + money.getNumberOfTicket() + "개 이하여야 합니다.");
        }
        this.numberOfManualTicket = numberOfManualTicket;
    }

    public int getNumberOfManualTicket() {
        return numberOfManualTicket;
    }

    public void addManualLotto(List<Integer> manualLotto) {
        lottos.add(ManualLottoGenerator.create(manualLotto));
    }

    public boolean isCreatedNumberOfManualLotto() {
        return lottos.size() == numberOfManualTicket;
    }

    public void createAutoLottos() {
        lottos.add(AutoLottoGenerator.create());
    }

    public boolean isCreatedNumberOf(int numberOfTicket) {
        return lottos.size() == numberOfTicket;
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
