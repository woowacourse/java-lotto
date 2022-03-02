package controller;

import static util.LottoCountUtils.getValidAutosCount;
import static util.LottoCountUtils.getValidTotalCount;
import static util.LottoNumberUtils.registerBonusNumber;
import static util.LottoNumberUtils.registerWinningNumbers;

import dto.LottoCountsDto;
import domain.LottoNumber;
import domain.LottoReferee;
import domain.Lottos;
import java.util.List;

public class LottoController {

    public LottoCountsDto initCountsDto(int totalLottoPrice, int manualsCount) {
        int totalCount = getValidTotalCount(totalLottoPrice);
        int autosCount = getValidAutosCount(manualsCount, totalCount);

        return new LottoCountsDto(manualsCount, autosCount);
    }

    public Lottos initLottos(List<String> manualsRaw, LottoCountsDto countsDto) {
        return Lottos.of(manualsRaw, countsDto);
    }

    public LottoReferee initLottoReferee(String winningNumbersInput, int bonusBallValue) {
        List<LottoNumber> winningNumbers = registerWinningNumbers(winningNumbersInput);
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers, bonusBallValue);

        return new LottoReferee(winningNumbers, bonusNumber);
    }
}
