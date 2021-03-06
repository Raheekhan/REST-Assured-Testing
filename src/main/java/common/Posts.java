package common;

public class Posts {

    private int id;
    private String title;
    private String author;
    private Info[] info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Info[] getInfo() {
        return info;
    }

    public void setInfo(Info[] info) {
        this.info = info;
    }
}
