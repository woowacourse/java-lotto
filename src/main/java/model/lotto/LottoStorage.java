package model.lotto;

import java.util.ArrayList;
import java.util.List;

import model.result.WinningResult;
import model.winningnumber.LottoWinningNumberDTO;
import model.bonusball.BonusBallDTO;

public class LottoStorage {
    private final List<Lotto> lottoStorage;

    public LottoStorage(LottoCount lottoCount) {
        this.lottoStorage = store(lottoCount);
    }

    private List<Lotto> store(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        while (!lottoCount.isZero()) {
            lottos.add(new Lotto(RandomLottoNumbersGenerator.pickSixNumbers()));
            lottoCount.makeLotto();
        }
        return lottos;
    }

    public List<LottoDTO> getLottoStorageDTO() {
        List<LottoDTO> lottoDTOS = new ArrayList<>();
        lottoStorage.forEach(lotto -> lottoDTOS.add(lotto.getLottoDTO()));
        return lottoDTOS;
    }

    public WinningResult calcWinningNumber(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumbersDTO) {
        WinningResult winningResult = new WinningResult();
        lottoStorage.forEach(lotto -> lotto.calcWinningNumber(winningResult, bonusBallDTO, winningNumbersDTO));
        return winningResult;
    }
}
