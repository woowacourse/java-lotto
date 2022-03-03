package lotto.view;

import java.util.List;

import lotto.client.InputClient;
import lotto.config.ClientConfig;

public class InputView {

    private final InputClient client;

    private InputView(InputClient client) {
        this.client = client;
    }

    private static class InputViewHelper {
        private static final InputView INSTANCE = new InputView(ClientConfig.getInputClient());
    }

    public static InputView getInstance() {
        return InputViewHelper.INSTANCE;
    }

    public String inputMoney() {
        return client.input("구입금액을 입력해 주세요.\n");
    }

    public List<String> inputLottoNumbers() {
        int lottoCount = Integer.parseInt(client.input("수동으로 구매할 로또 수를 입력해 주세요."));
        return client.inputRepeat("수동으로 구매할 번호를 입력해 주세요.", lottoCount);
    }

    public String inputWinningNumber() {
        return client.input("지난 주 당첨 번호를 입력해 주세요.\n");
    }

    public String inputBonusBall() {
        return client.input("보너스 볼을 입력해 주세요.\n");
    }
}
