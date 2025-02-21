package model;

import dto.LottoDto;
import dto.LottosDto;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResults evaluateLottos(WinningNumbers winningNumbers) {
        List<LottoResult> lottoResults = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoResult lottoResult = new LottoResult(lotto);
            lottoResult.evaluateLotto(winningNumbers);
            lottoResults.add(lottoResult);
        }
        return new LottoResults(lottoResults);
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoDtos.add(lotto.toDto());
        }
        return new LottosDto(lottoDtos);
    }
}
