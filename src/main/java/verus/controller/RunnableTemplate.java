package verus.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;
import verus.exception.ApplicationFinishedException;
import verus.view.InputTemplate;

public class RunnableTemplate {

    private final InputTemplate inputTemplate;

    public RunnableTemplate(InputTemplate inputTemplate) {
        this.inputTemplate = inputTemplate;
    }

    public RunnableTemplate(InputStream inputStream, OutputStream outputStream) {
        inputTemplate = new InputTemplate(inputStream, outputStream);
    }

    public void repeatablyRun(Runnable runnable, Consumer<? super Exception> handler,
        Class<? extends Exception> cls) {
        try {
            runnable.run();
        } catch (Exception e) {
            handleException(() -> repeatablyRun(runnable, handler, cls), handler, cls, e);
        }
    }

    private void handleException(Runnable runnable, Consumer<? super Exception> handler,
        Class<? extends Exception> cls, Exception thrown) {
        if (cls.isAssignableFrom(thrown.getClass())) {
            handler.accept(thrown);
            selectivelyRepeat(runnable);
        }
        throw new ApplicationFinishedException(thrown);
    }

    public <T> T repeatablyRun(Supplier<T> supplier, Consumer<? super Exception> handler,
        Class<? extends Exception> cls) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return handleException(() -> repeatablyRun(supplier, handler, cls), handler, cls, e);
        }
    }

    private <T> T handleException(Supplier<T> supplier, Consumer<? super Exception> handler,
        Class<? extends Exception> cls, Exception thrown) {
        if (cls.isAssignableFrom(thrown.getClass())) {
            handler.accept(thrown);
            return selectivelyRepeat(supplier);
        }
        throw new ApplicationFinishedException(thrown);
    }

    private <T> T selectivelyRepeat(Supplier<T> supplier) {
        String value = inputTemplate.chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (isRepeatable(value)) {
            return supplier.get();
        }
        throw new ApplicationFinishedException();
    }

    private void selectivelyRepeat(Runnable runnable) {
        String value = inputTemplate.chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (isRepeatable(value)) {
            runnable.run();
        }
        throw new ApplicationFinishedException();
    }

    private boolean isRepeatable(String value) {
        return value.equals("y") || value.equals("Y");
    }
}
