package service;

import domain.WinningLotto;
import dto.WinningLottoDto;
import java.util.List;

public class OpenLottoService {

    public WinningLottoDto makeWinningLotto(List<Integer> numbers, Integer bonusNumber){
        WinningLotto winningLotto = new WinningLotto(numbers,bonusNumber);
        return winningLotto.getWinningLottoDto();
    }

}
