package io.lumeer.core.model.types.text;

public class TextExample {

    private String text;

    public TextExample() {
    }

    public TextExample(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextExample{" +
                "text='" + text + '\'' +
                '}';
    }
}
