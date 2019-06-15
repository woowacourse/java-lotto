package lottoGame.lotto;

import domain.Lotto;
import domain.LottoFactory;
import domain.Number;

import java.util.Arrays;

public class LottoAssembler {

    public static LottoDTO makeDTOFrom(Lotto lotto, int token, int idx, boolean isAuto) {
        return new LottoDTO(
                idx,
                lotto.get(0).getNumber(),
                lotto.get(1).getNumber(),
                lotto.get(2).getNumber(),
                lotto.get(3).getNumber(),
                lotto.get(4).getNumber(),
                lotto.get(5).getNumber(),
                token,
                isAuto
        );
    }

    public static Lotto makeFrom(LottoDTO lottoDTO) {
        return LottoFactory.createLottoFromNumbers(Arrays.asList(
                Number.from(lottoDTO.getNo1()),
                Number.from(lottoDTO.getNo2()),
                Number.from(lottoDTO.getNo3()),
                Number.from(lottoDTO.getNo4()),
                Number.from(lottoDTO.getNo5()),
                Number.from(lottoDTO.getNo6())
        ));
    }
}
