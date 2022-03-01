package domain;

import domain.generator.AutoLottoGenerator;
import dto.LottoDto;
import dto.ResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "보너스 볼 번호가 지난 주 당첨 번호와 일치할 수 없습니다.";
    private static final String ERROR_LOTTO_SIZE_MESSAGE = "번호는 6개를 입력하셔야 합니다.";
    private static final String WIN_PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 %s라는 의미임)";
    private static final String PROFIT_NEGATIVE_MESSAGE = "손해";
    private static final String PROFIT_POSITIVE_MESSAGE = "이익";
    private static final String SYSTEM_SEPERATOR = "%n";

    private final Money money;
    private Lotto lastWinLotto;
    private List<Lotto> issuedLotto;

    public LottoService(final int money) {
        this.money = new Money(money);
    }

    public void initLastWinLotto(final List<String> inputLotto) {
        validate(inputLotto);
        this.lastWinLotto = Lotto.fromInput(inputLotto);
    }

    private void validate(final List<String> inputLotto) {
        if (inputLotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE_MESSAGE);
        }
    }

    public void issueLotto() {
        this.issuedLotto = LottoFactory.generateLotto(money.calculateCounts(), new AutoLottoGenerator());
    }

    public List<LottoDto> getIssuedLotto() {
        return issuedLotto.stream()
            .map(LottoDto::from)
            .collect(Collectors.toUnmodifiableList());
    }

    public ResultDto calculateResult(final int bonusNumberInput) {
        final LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
        if (isBonusNumberContain(this.lastWinLotto, bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
        }

        //1. 재료들이 모아지면, 결과를 뽑아줄 클래스의 생성자로 받아들이기
        // -> map으로 카운팅할 것은 이미 예상하지만, 그것을 인변으로 관리하고 + 로직을 담을 클래스
        // my) input파라미터로 넘어온 보너스번호를 저장하지말고 바로 가보자.
        return new ResultDto(this.issuedLotto, this.lastWinLotto, bonusNumber);
    }

    private boolean isBonusNumberContain(final Lotto lotto, final LottoNumber bonusNumber) {
        return lotto.isContainNumber(bonusNumber);
    }

    public String getProfitOrNotMessage(final ResultDto resultDto) {
        final double profitRate = money.calculateProfit(resultDto.getPrizeProfit());
        String resultMessage = PROFIT_NEGATIVE_MESSAGE;
        if (profitRate >= 1) {
            resultMessage = PROFIT_POSITIVE_MESSAGE;
        }
        return String.format((WIN_PROFIT_RESULT_MESSAGE) + SYSTEM_SEPERATOR, profitRate, resultMessage);
    }
}
