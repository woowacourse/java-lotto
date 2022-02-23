import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int amount) {
        this.lottos = new ArrayList<>();
        while (amount-- > 0) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
        }
    }

    public Result getResult(List<Number> winningNumbers, Number bonusNumber) {
        Result result = new Result();

        for (Lotto lotto : lottos) {
            WinningPrice winningPrice = lotto.getWinningPrice(winningNumbers, bonusNumber);
            result.add(winningPrice);
        }

        return result;
    }

    int getCount() {
        return lottos.size();
    }
}
