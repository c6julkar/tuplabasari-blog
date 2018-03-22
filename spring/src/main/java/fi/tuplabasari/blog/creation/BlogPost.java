package fi.tuplabasari.blog.creation;

public class BlogPost {
    private int id;
    private static int ENTIRES;
    private String header;
    private String content;


    public BlogPost() {

    }

    public BlogPost(String header, String content) {
        ENTIRES++;
        this.header = header;
        this.content = content;
        this.id = ENTIRES;
    }

    public BlogPost(int id, String header, String content) {
        this.header = header;
        this.content = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }
}
