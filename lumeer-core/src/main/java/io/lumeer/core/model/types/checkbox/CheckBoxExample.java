package io.lumeer.core.model.types.checkbox;

public class CheckBoxExample {

    private boolean selected;

    public CheckBoxExample() {
        this(false);
    }

    public CheckBoxExample(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "CheckBoxExample{" +
                "selected=" + selected +
                '}';
    }
}
