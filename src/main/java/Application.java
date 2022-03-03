import controller.LottoController;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import domain.Result;
=======
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
=======
import domain.Result;
>>>>>>> 7eb4fa3 (refactor: 프린트 할 결과물 생성 로직 변경)
=======
=======
import domain.Lotto.LottoNumber;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
>>>>>>> 107d435 (refactor : purchaseLotto의 파라미터로 외부에서 LottoGenerator 구현체를 생성해서 주입해주면서 메서드를 재사용하는 형식으로 리팩토링)
import dto.LottoCountDto;
>>>>>>> 8334008 (feat : 수동 로또 구매 기능 추가)
import dto.LottosDto;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
<<<<<<< HEAD
<<<<<<< HEAD
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        LottoController lottoController = new LottoController();

        LottosDto lottosDto = lottoController.purchase(inputView.inputPurchaseAmount());
        outputView.printPurchasedLotto(lottosDto);

        lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());

        outputView.printResult(lottoController.makeResult());
=======
=======
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        LottoController lottoController = new LottoController();

        LottoCountDto lottoCountDto = lottoController.selectLottoCount(inputView.inputPurchaseAmount(), inputView.inputManualLottoCount());

        lottoController.purchaseLotto(new ManualLottoGenerator(), inputView.inputManualLottoNumber(lottoCountDto.getManualLottoCount()));
        LottosDto lottosDto = lottoController.purchaseLotto(new AutoLottoGenerator(), null);

        outputView.printPurchasedLotto(lottoCountDto, lottosDto);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        List<String> winningNumber = InputView.inputWinningNumber();
        int bonusBall = InputView.inputBonusBall();
        lottoController.determineWinningNumber(winningNumber,bonusBall);
<<<<<<< HEAD
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
=======

<<<<<<< HEAD
<<<<<<< HEAD
        lottoController.calculateRank();
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
=======
        List<RankDto> rankDtos = lottoController.calculateRank();

        OutputView.printRank(rankDtos);
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
=======
        RanksDto result = lottoController.makeResult();
        OutputView.printResult(result);
>>>>>>> 6741479 (feat: 수익률 계산 로직 및 출력 기능 구현)
=======
        lottoController.determineWinningNumber(InputView.inputWinningNumber(),InputView.inputBonusBall());
=======
        lottoController.determineWinningNumber(InputView.inputWinningNumber(), InputView.inputBonusBall());
>>>>>>> 94c4d43 (style: 코드 포멧팅)

<<<<<<< HEAD
        List<Result> results = lottoController.judgeLottos();
        OutputView.printResult(lottoController.makeResult(results));
>>>>>>> 7eb4fa3 (refactor: 프린트 할 결과물 생성 로직 변경)
=======
        OutputView.printResult(lottoController.makeResult());
>>>>>>> a879dd3 (feat : 구매한 모든 로또의 결과를 기록하는 클래스 추가)
=======
        lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());

        outputView.printResult(lottoController.makeResult());
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
    }
}
