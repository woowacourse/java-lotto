package lotto.domain.game;

import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class TotalLottoGames {
    private ManualLottoGames manualLottoGames;
    private AutoLottoGames autoLottoGames;

    public TotalLottoGames(Count autoCount) {
        this.manualLottoGames = new ManualLottoGames();
        this.autoLottoGames = new AutoLottoGames(autoCount);
    }

    public void addManual(List<Number> numbers) {
        manualLottoGames.addLotto(numbers);
    }

    public int autoSize() {
        return autoLottoGames.size();
    }

    public int manualSize() {
        return manualLottoGames.size();
    }

    public List<Lotto> getAllGames() {
        return manualLottoGames.combine(autoLottoGames);
    }
}
