package lotto;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lotto.domain.WinningType;

import java.io.IOException;

public class MyEnumAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (rawType == WinningType.class) {
            return new MyEnumTypeAdapter<T>();
        }
        return null;
    }

    public class MyEnumTypeAdapter<T> extends TypeAdapter<T> {

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            WinningType status = (WinningType) value;
            // Here write what you want to the JsonWriter.
            out.beginObject();
            out.name("value");
            out.value(status.name());
            out.name("matchNum");
            out.value(status.getMatchNum());
            out.name("reward");
            out.value(status.getReward());
            out.endObject();
        }

        public T read(JsonReader in) throws IOException {
            // Properly deserialize the input (if you use deserialization)
            return null;
        }
    }

}