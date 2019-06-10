package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoNumberException;
import lottogame.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<Lotto> lottos = new ArrayList<>();
    private final int numberOfManualTicket;

    private LottoTickets(int numberOfManualTicket, Money money) {
        if (!money.isValidateAmount(numberOfManualTicket)) {
            throw new InvalidLottoNumberException("수동 구매 수는 " + money.getNumberOfTicket() + "개 이하여야 합니다.");
        }
        this.numberOfManualTicket = numberOfManualTicket;
    }

    public static LottoTickets generate(String numberOfMannualTicket, Money money) {
        try {
            return new LottoTickets(Integer.parseInt(numberOfMannualTicket), money);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getNumberOfManualTicket() {
        return numberOfManualTicket;
    }

    public void addManualLotto(String manualLotto) {
        Lotto lotto = ManualLottoGenerator.create(manualLotto);
        if(lotto != null){
            lottos.add(lotto);
        }
    }

    public boolean isPossibleCreateManualLotto() {
        return lottos.size() < numberOfManualTicket;
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
