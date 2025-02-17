package view;

import domain.Lotto;
import dto.WinningRecipe;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String USER_LOTTOS_HEADER = "%d개를 구매했습니다.";
    private static final String ASK_INPUT_WINNING_LOTTO = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_RESULT_HEADER = "당첨 통계" + LINE_SEPARATOR + "---------";
    private static final String WINNING_RECIPE = "%d개 일치 (%,d원) - %d개";
    private static final String WINNING_RECIPE_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    private static final String WINNING_PROFIT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";

    public void printAskInputMoney() {
        printlnMessage(ASK_INPUT_MONEY);
    }

    public void printUserLottos(final List<Lotto> lottos) {
        final String message = lottos.stream()
                .map(lotto -> lotto.getNumbers().toString())
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(String.format(USER_LOTTOS_HEADER, lottos.size()) + LINE_SEPARATOR + message);
    }

    public void printAskInputWiningLotto() {
        printlnMessage(LINE_SEPARATOR + ASK_INPUT_WINNING_LOTTO);
    }

    public void printAskInputBonusNumber() {
        printlnMessage(ASK_INPUT_BONUS_NUMBER);
    }

    public void printWinningResult(final List<WinningRecipe> winningRecipes) {
        printlnMessage(LINE_SEPARATOR + WINNING_RESULT_HEADER);
        final String message = winningRecipes.stream()
                .map(this::createWinningRecipeMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(message);
    }


    public void printWinningProfit(double profitRate) {
        String message = String.format(WINNING_PROFIT, profitRate, "이득");
        if (profitRate < 1) {
            message = String.format(WINNING_PROFIT, profitRate, "손해");
        }
        printlnMessage(message);
    }

    private String createWinningRecipeMessage(final WinningRecipe winningRecipe) {
        String message = WINNING_RECIPE;
        if (isRankTwo(winningRecipe)) {
            message = WINNING_RECIPE_WITH_BONUS;
        }
        return String.format(message, winningRecipe.matchCount(), winningRecipe.price(),
                winningRecipe.matchQuantity());
    }

    private boolean isRankTwo(final WinningRecipe winningRecipe) {
        return winningRecipe.rank() == 2;
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

}
