package io.lumeer.core.model.types.link;

public class LinkExample {

    private String link;
    private String title;

    public LinkExample() {
    }

    public LinkExample(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LinkExample{" +
                "link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
