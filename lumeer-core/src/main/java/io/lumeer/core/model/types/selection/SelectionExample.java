package io.lumeer.core.model.types.selection;

import java.util.List;

public class SelectionExample {

    private List<String> selected;

    public SelectionExample() {
    }

    public SelectionExample(List<String> selected) {
        this.selected = selected;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "SelectionExample{" +
                "selected=" + selected +
                '}';
    }
}
