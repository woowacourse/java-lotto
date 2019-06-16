package lotto.domain;

import lotto.dto.WinningLottoDTO;

import java.util.Objects;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateWinningLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateWinningLotto(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼이 이미 입력된 로또 숫자와 중복됩니다.");
        }
    }

    Rank match(Lotto userLotto) {
        int countOfMatch = lotto.compareTo(userLotto);
        boolean matchBonus = userLotto.contains(bonusBall);

        return Rank.valueOf(countOfMatch, matchBonus);
    }

    public int getLottoNumber(int index) {
        return lotto.getLottoNumber(index);
    }

    public int getBonusNumber() {
        return bonusBall.getNumber();
    }

    public WinningLottoDTO toDTO(int round) {
        WinningLottoDTO winningLottoDto = new WinningLottoDTO();
        winningLottoDto.setRound(round);
        winningLottoDto.setNumber1(lotto.getLottoNumber(0));
        winningLottoDto.setNumber2(lotto.getLottoNumber(1));
        winningLottoDto.setNumber3(lotto.getLottoNumber(2));
        winningLottoDto.setNumber4(lotto.getLottoNumber(3));
        winningLottoDto.setNumber5(lotto.getLottoNumber(4));
        winningLottoDto.setNumber6(lotto.getLottoNumber(5));
        winningLottoDto.setBonus(bonusBall.getNumber());
        return winningLottoDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusBall);
    }
}
