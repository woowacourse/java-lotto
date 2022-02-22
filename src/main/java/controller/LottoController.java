package controller;

import static validator.LottoNumberValidators.validateAndParseNumber;
import static validator.LottoNumberValidators.validateNoDuplicateInList;
import static validator.LottoNumberValidators.validateNoDuplicates;

import domain.LottoGame;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.LottoNumberValidators;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        initLottoGame();
        String winningNumbers = InputView.requestWinningNumbers();
        List<Integer> nums = Arrays.stream(winningNumbers.split(", "))
                .map(LottoNumberValidators::validateAndParseNumber)
                .collect(Collectors.toList());
        if (nums.size() != 6) {
            throw new IllegalArgumentException("6개의 당첨 번호를 입력해야 합니다.");
        }
        validateNoDuplicates(nums);

        int bonusNumber = InputView.requestBonusNumber();
        validateNoDuplicateInList(bonusNumber, nums);
    }

    private void initLottoGame() {
        int money = InputView.requestUserMoney();
        int count = money/1000;
        LottoGame lottoGame = LottoGame.create(count);
        OutputView.printPurchaseInfo(lottoGame.getLottos());
    }
}
