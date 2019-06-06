package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final LottoNumberGroup winningNums;
    private final int bonus;

    public WinningLotto(LottoNumberGroup winningNums, int bonus) {
        this.winningNums = winningNums;
        this.bonus = bonus;
        if (winningNums.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 다른 숫자와 겹칠 수 없습니다.");
        }
    }

    public LottoResult match(Lotto lotto) {
        return LottoResult.valueOf(lotto.countMatch(winningNums), lotto.contains(bonus));
    }

    public WinningLottoDto toDto() {
        WinningLottoDto dto = new WinningLottoDto();
        List<Integer> winningNumbers = new ArrayList<>();
        winningNums.forEachNumbers(winningNumbers::add);
        dto.setWinningNumber0(winningNumbers.get(0));
        dto.setWinningNumber1(winningNumbers.get(1));
        dto.setWinningNumber2(winningNumbers.get(2));
        dto.setWinningNumber3(winningNumbers.get(3));
        dto.setWinningNumber4(winningNumbers.get(4));
        dto.setWinningNumber5(winningNumbers.get(5));
        dto.setWinningBonusNumber(bonus);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonus == that.bonus &&
            Objects.equals(winningNums, that.winningNums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNums, bonus);
    }
}
