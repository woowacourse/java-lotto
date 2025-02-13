package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LottoApplicationTest {
    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(1L);
    private static final String ERROR_MESSAGE = "[ERROR]";

    private PrintStream standardOut;
    private OutputStream result;

    @BeforeEach
    public final void init() {
        this.standardOut = System.out;
        this.result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.result));
    }

    @AfterEach
    public final void printOutput() {
        System.setOut(this.standardOut);
        System.out.println(this.output());
    }

    @DisplayName("잘못된 구매 금액 입력에 대한 예외")
    @Test
    void checkException() {
        assertException(() -> {
            runException("1000원");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    public static void assertException(Executable executable) {
        assertTimeoutPreemptively(TIMEOUT_SECONDS, executable);
    }

    private void runException(String... args) {
        try {
            this.run(args);
        } catch (NoSuchElementException ignored) {
        }
    }

    private void run(String... args) {
        this.input(args);
        this.runApplication();
    }

    private void input(String... args) {
        byte[] buffer = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buffer));
    }

    private void runApplication() {
        LottoApplication.main(new String[]{});
    }

    private String output() {
        return this.result.toString().trim();
    }
}
