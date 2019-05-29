package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGames {
    private ManualLottoGames manualLottoGames;
    private AutoLottoGames autoLottoGames;

    public LottoGames(AutoCount autoCount) {
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

    public List<Lotto> getAll() {
        List<Lotto> lottoGames = new ArrayList<>();
        lottoGames.addAll(manualLottoGames.getLottoGames());
        lottoGames.addAll(autoLottoGames.getLottoGames());
        return lottoGames;
    }
}
