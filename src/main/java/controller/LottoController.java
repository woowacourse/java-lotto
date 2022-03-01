package controller;

<<<<<<< HEAD
<<<<<<< HEAD
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import domain.Result;
import domain.ResultStatus;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;
=======
import domain.*;
=======
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import domain.Result;
import domain.ResultStatus;
import domain.player.Player;
import dto.LottosDto;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
=======
import dto.RankDto;
<<<<<<< HEAD
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
=======
=======
>>>>>>> 94c4d43 (style: 코드 포멧팅)
import dto.RanksDto;
>>>>>>> 6741479 (feat: 수익률 계산 로직 및 출력 기능 구현)

import java.util.List;

public class LottoController {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)

    private Player player;
    private WinningLotto winningLotto;

<<<<<<< HEAD
    public LottosDto purchase(int purchaseAmount) {
        player = new Player(purchaseAmount);
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
        return LottosDto.from(player.getLottos());
    }

    public void determineWinningNumber(List<Integer> winningNumbers, int bonusBall) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(winningNumbers);
        this.winningLotto = new WinningLotto(winningLotto, LottoNumber.valueOf(bonusBall));
    }

    public RanksDto makeResult() {
        List<Result> results = player.judgeAll(winningLotto);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.judgeResult(results);
        double totalIncome = resultStatus.calculateTotalIncome();
        double incomeRate = player.calculateIncomeRate(totalIncome);
        return RanksDto.from(resultStatus, incomeRate);
=======
=======
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
    public LottosDto purchase(int purchaseAmount) {
        player = new Player(purchaseAmount);
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
        return LottosDto.from(player.getLottos());
    }

<<<<<<< HEAD
    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
<<<<<<< HEAD
        List<LottoNumber> lottoNumbers = LottoFactory.generateWinningLotto(winningNumber);
<<<<<<< HEAD
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
=======
        winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
=======
=======
    public void determineWinningNumber(List<Integer> winningNumbers, int bonusBall) {
<<<<<<< HEAD
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
<<<<<<< HEAD
        Lotto winningLotto = lottoGenerator.generateLotto(winningNumbers);
        this.winningLotto = new WinningLotto(winningLotto, new LottoNumber(bonusBall));
>>>>>>> 5b2a52c (refactor: 로또 생성 기능 인터페이스로 분리)
=======
        Lotto winningLotto = lottoGenerator.generateWinningLotto(winningNumbers);
=======
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(winningNumbers);
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
        this.winningLotto = new WinningLotto(winningLotto, LottoNumber.valueOf(bonusBall));
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
    }

    public RanksDto makeResult() {
        List<Result> results = player.judgeAll(winningLotto);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.judgeResult(results);
        double totalIncome = resultStatus.calculateTotalIncome();
        double incomeRate = player.calculateIncomeRate(totalIncome);
<<<<<<< HEAD
        System.out.println("incomeRate = " + incomeRate);

<<<<<<< HEAD
<<<<<<< HEAD
        List<RankDto> rankDtos = new ArrayList<>();
        int rankNumber = 5;

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank, rankNumber--));
        }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
=======
        return rankDtos;
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
    }

    private double calculateIncomeRate(List<RankDto> rankDtos) {
        double totalIncome = 0;
        for (RankDto rankDto : rankDtos) {
            totalIncome += rankDto.getReward() * rankDto.getHitCount();
        }
        return totalIncome / player.getMoney();
=======
        return new RanksDto(rankDtos, incomeRate);
>>>>>>> 7eb4fa3 (refactor: 프린트 할 결과물 생성 로직 변경)
=======
        return new RanksDto(incomeRate);
>>>>>>> 2b5d619 (refactor: 결과 리스트 생성 로직 이동)
=======
        return RanksDto.from(incomeRate);
>>>>>>> 1b78799 (refactor : Dto 생성자 private 으로 접근지정자 변경)
=======
        return RanksDto.from(resultStatus, incomeRate);
>>>>>>> a879dd3 (feat : 구매한 모든 로또의 결과를 기록하는 클래스 추가)
    }
}
