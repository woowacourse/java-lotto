package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.utils.Converter;

public class WinningLottoDto {
    private Lotto lotto;
    private LottoNumber bonus;

    public WinningLottoDto(String lotto, String bonus) {
        this.lotto = convertLotto(lotto);
        this.bonus = convertLottoNumber(bonus);
    }

    private LottoNumber convertLottoNumber(String bonus) {
        return LottoNumber.valueOf(Integer.parseInt(bonus));
    }

    private Lotto convertLotto(String lotto) {
        return new Lotto(Converter.convertNumbers(lotto));

    }

    public WinningLotto getWinningLotto() {
        return new WinningLotto(lotto,bonus);
    }
}
