package lottoGame.lotto;

import domain.Lotto;
import domain.LottoFactory;
import domain.Number;
import domain.WinningLotto;

import java.util.Arrays;

public class WinningLottoAssembler {
    public static WinningLottoDTO makeDTOFrom(WinningLotto winningLotto, int token) {
        return new WinningLottoDTO(
                winningLotto.getBonusNumber().getNumber(),
                winningLotto.getLotto().get(0).getNumber(),
                winningLotto.getLotto().get(1).getNumber(),
                winningLotto.getLotto().get(2).getNumber(),
                winningLotto.getLotto().get(3).getNumber(),
                winningLotto.getLotto().get(4).getNumber(),
                winningLotto.getLotto().get(5).getNumber(),
                token
        );
    }

    public static WinningLotto makeFrom(WinningLottoDTO winningLottoDTO) {
        Lotto lotto = LottoFactory.createLottoFromNumbers(Arrays.asList(
                Number.from(winningLottoDTO.getNo1()),
                Number.from(winningLottoDTO.getNo2()),
                Number.from(winningLottoDTO.getNo3()),
                Number.from(winningLottoDTO.getNo4()),
                Number.from(winningLottoDTO.getNo5()),
                Number.from(winningLottoDTO.getNo6())
        ));
        return WinningLotto.of(lotto, Number.from(winningLottoDTO.getBonusNo()));
    }
}
