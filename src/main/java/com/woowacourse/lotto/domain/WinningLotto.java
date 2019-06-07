package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final LottoNumberGroup winningNums;
    private final LottoNumber bonus;

    public WinningLotto(LottoNumberGroup winningNums, LottoNumber bonus) {
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
        List<LottoNumber> winningNumbers = new ArrayList<>();
        winningNums.forEachNumbers(winningNumbers::add);
        dto.setWinningNumber0(winningNumbers.get(0).toInt());
        dto.setWinningNumber1(winningNumbers.get(1).toInt());
        dto.setWinningNumber2(winningNumbers.get(2).toInt());
        dto.setWinningNumber3(winningNumbers.get(3).toInt());
        dto.setWinningNumber4(winningNumbers.get(4).toInt());
        dto.setWinningNumber5(winningNumbers.get(5).toInt());
        dto.setWinningBonusNumber(bonus.toInt());
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningNums, that.winningNums) &&
            Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNums, bonus);
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("WinningLotto { winningNums: ")
            .append(winningNums)
            .append("bonus: ")
            .append(bonus)
            .toString();

    }
}
