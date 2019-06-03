package lotto.domain.lotto;

import lotto.domain.InvalidBounusBall;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BonusBall {
    private static final String BONUS_PATTERN = "^[0-9]*$";
    private int bonus;

    public BonusBall(Lotto winningLotto, String bonus){
        this.bonus = invalidBonusBall(winningLotto, bonus);
    }

    private int invalidBonusBall(Lotto winningLotto, String bonus){
        Matcher matcher = Pattern.compile(BONUS_PATTERN).matcher(bonus);
        if(!matcher.find()){
            throw new InvalidBounusBall("옳바르지 않은 입력입니다.");
        }
        if(winningLotto.getLottoNumbers().contains(new LottoNumber(Integer.parseInt(bonus)))){
            throw new InvalidBounusBall("당첨번호와 중복된 정수가 있습니다.");
        }
        return Integer.parseInt(bonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusBall bonusBall = (BonusBall) o;
        return bonus == bonusBall.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonus);
    }
}
