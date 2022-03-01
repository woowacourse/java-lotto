package lotto.view;

import lotto.client.ErrorClient;
import lotto.config.ClientConfig;

public class ErrorView {

    private static final String PREFIX = "[ERROR] ";

    private final ErrorClient errorClient;

    private ErrorView(ErrorClient errorClient) {
        this.errorClient = errorClient;
    }

    private static class ErrorViewHelper {
        private static final ErrorView INSTANCE = new ErrorView(ClientConfig.getErrorClient());
    }

    public static ErrorView getInstance() {
        return ErrorViewHelper.INSTANCE;
    }

    public void error(String message) {
        errorClient.error(PREFIX + message + "\n");
    }
}
