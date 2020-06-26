package me.panpf.jsonx;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonFormatter {
    private static final String DEFAULT_INDENTATION = "    ";

    @NotNull
    private final String indentation;

    public JsonFormatter(@NotNull String indentation) {
        this.indentation = indentation;
    }

    public JsonFormatter() {
        this(DEFAULT_INDENTATION);
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public String format(@Nullable JSONObject jsonObject) {
        if (jsonObject == null) return "{}";
        return appendJsonObject(new StringBuilder(), jsonObject, 0).toString();
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public String format(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() <= 0) return "[]";
        return appendJsonArray(new StringBuilder(), jsonArray, 0).toString();
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public String format(@Nullable String json) throws JSONException {
        if (Jsonx.isEmpty(json)) {
            if (Jsonx.isArray(json)) {
                return "[]";
            } else if (Jsonx.isObject(json)) {
                return "{}";
            } else {
                return "{}";
            }
        }

        try {
            return format(new JSONObject(json));
        } catch (JSONException ignored) {
        }

        try {
            return format(new JSONArray(json));
        } catch (JSONException ignored) {
        }

        throw new JSONException("Invalid json: " + json);
    }

    @NotNull
    private StringBuilder appendJsonObject(@NotNull StringBuilder builder, @NotNull JSONObject jsonObject, int indentationCount) {
        builder.append("{");

        int newIndentationCount = indentationCount + 1;
        boolean hasData = false;

        //noinspection unchecked
        Iterator<String> keyIterator = jsonObject.keys();
        while (keyIterator.hasNext()) {
            hasData = true;
            String key = keyIterator.next();
            Object value = jsonObject.opt(key);

            builder.append("\n");
            appendIndentation(builder, newIndentationCount);

            builder.append("\"").append(key).append("\"").append(":");

            if (value instanceof JSONArray) {
                appendJsonArray(builder, (JSONArray) value, newIndentationCount);
            } else if (value instanceof JSONObject) {
                appendJsonObject(builder, (JSONObject) value, newIndentationCount);
            } else if (value instanceof String) {
                builder.append("\"").append(value.toString()).append("\"");
            } else if (value != null) {
                builder.append(value.toString());
            }

            if (keyIterator.hasNext()) {
                builder.append(",");
            }
        }

        if (hasData) {
            builder.append("\n");
        }
        appendIndentation(builder, indentationCount);
        builder.append("}");

        return builder;
    }

    @NotNull
    private StringBuilder appendJsonArray(@NotNull StringBuilder builder, @NotNull JSONArray jsonArray, int indentationCount) {
        builder.append("[");

        int newIndentationCount = indentationCount + 1;
        boolean hasData = false;

        for (int w = 0, size = jsonArray.length(); w < size; w++) {
            hasData = true;
            Object item = jsonArray.opt(w);

            builder.append("\n");
            appendIndentation(builder, newIndentationCount);

            if (item instanceof JSONArray) {
                appendJsonArray(builder, (JSONArray) item, newIndentationCount);
            } else if (item instanceof JSONObject) {
                appendJsonObject(builder, (JSONObject) item, newIndentationCount);
            } else if (item instanceof String) {
                builder.append("\"").append(item.toString()).append("\"");
            } else if (item != null) {
                builder.append(item.toString());
            }

            if (w < size - 1) {
                builder.append(",");
            }
        }

        if (hasData) {
            builder.append("\n");
        }
        appendIndentation(builder, indentationCount);
        builder.append("]");

        return builder;
    }

    private void appendIndentation(@NotNull StringBuilder builder, int indentationCount) {
        for (int w = 0; w < indentationCount; w++) {
            builder.append(indentation);
        }
    }
}
