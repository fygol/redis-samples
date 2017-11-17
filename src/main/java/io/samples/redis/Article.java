package io.samples.redis;

public class Article {
    private long id;

    private String name;

    private long votes;

    public Article(long id, String name, long votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
