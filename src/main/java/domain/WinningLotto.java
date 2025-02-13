package domain;

import util.Validator;

import java.util.*;

public class WinningLotto {
    private List<Integer> winningNumbers = new ArrayList<>();
    private int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateSize(winningNumbers);
        validateNumberRange(winningNumbers, bonusNumber);
        validateDuplicate(winningNumbers, bonusNumber);



        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateSize(List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6){
            throw new IllegalArgumentException("당첨번호는 6자리만 가능합니다.");
        }
    }


    private void validateNumberRange(List<Integer> winningNumbers, int bonusNumber) {
        for(int number : winningNumbers){
            checkNumberRange(number);
        }
        checkNumberRange(bonusNumber);
    }

    private void validateDuplicate(List<Integer> winningNumbers, int bonusNumber) {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if(set.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }

        if(winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void checkNumberRange(int number) {
        if(number < 1 || number > 50){
            throw new IllegalArgumentException("당첨 번호는 1~50 사이의 값만 가능합니다.");
        }
    }
}
