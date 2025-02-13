package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private List<Integer> lottoNumber;

    public Lotto() {
        this.lottoNumber = generateLotto();
    }

    public Lotto(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public void checkDuplicate(int bonusNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        while(lotto.size() < 6) {
            int number = generateRandomNumber();
            checkDuplicate(lotto, number);
        }
        Collections.sort(lotto);
        return lotto;
    }

    private int generateRandomNumber() {
        return (int) ((Math.random() * 45) + 1);
    }

    private void checkDuplicate(List<Integer> lotto, int number) {
        if (!lotto.contains(number)) {
            lotto.add(number);
        }
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + System.lineSeparator();
    }

    public int match(Lotto winningLottoNumber) {
        int count = 0;
        for (int number : lottoNumber) {
            count += winningLottoNumber.contain(number);
        }
        return count;
    }
    public int contain(int number) {
        if (lottoNumber.contains(number)) {
            return 1;
        }
        return 0;
    }

    public boolean checkBonusNumberMatch(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }

}
