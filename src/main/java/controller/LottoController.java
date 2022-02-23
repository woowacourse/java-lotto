package controller;

import domain.*;
import dto.LottosDto;

import java.util.List;

public class LottoController {
    public LottosDto purchase(int purchaseAmount) {
        Player player = new Player(new Money(purchaseAmount));
        List<Lotto> lottos = player.getLottos();
        return LottosDto.from(lottos);
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        List<LottoNumber> lottoNumbers = LottoFactory.generateWinningLotto(winningNumber);
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
    }
}
