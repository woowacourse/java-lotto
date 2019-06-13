package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private final UserLottos manualLottos;
    private final UserLottos autoLottos;
    private final WinningLotto winningLotto;

    public LottoGame(UserLottos manualLottos, UserLottos autoLottos, WinningLotto winningLotto) {
        this.manualLottos = manualLottos;
        this.autoLottos = autoLottos;
        this.winningLotto = winningLotto;
    }

    private List<Lotto> getAllUserLottos() {
        List<Lotto> allUserLottos = new ArrayList<>(manualLottos.getUserLottos());
        allUserLottos.addAll(autoLottos.getUserLottos());
        return allUserLottos;
    }

    public Map<Rank, Integer> getCountOfRank() {
        return winningLotto.calculateCountOfRank(getAllUserLottos());
    }

    public double calculateRateOfReturn() {
        return new Prize(getCountOfRank()).divideByMoney(getUserMoney());
    }

    private Money getUserMoney() {
        return new Money((manualLottos.getSize() + autoLottos.getSize()) * LOTTO_PRICE);
    }

    public List<String> gameResult() {
        Map<Rank, Integer> result=getCountOfRank();
        List<String> gameResultMessage=new ArrayList<>();
        result.forEach((rank, number) -> gameResultMessage.add(rank.toString()+" : "+number+"개"));
        return gameResultMessage;
    }
}
