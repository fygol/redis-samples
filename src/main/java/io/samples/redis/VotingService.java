package io.samples.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class VotingService {
    private Jedis redisClient;

    public VotingService(Jedis redisClient) {
        this.redisClient = redisClient;
    }

    public void upVote(long articleId) {
        redisClient.incr(votesKey(articleId));
    }

    public void downVote(long articleId) {
        redisClient.decr(votesKey(articleId));
    }

    public Article getArticleRating(long articleId) {
        String nameKey = nameKey(articleId);
        String votesKey = votesKey(articleId);
        List<String> values = redisClient.mget(nameKey, votesKey);

        return new Article(articleId, values.get(0), Long.valueOf(values.get(1)));
    }

    private String nameKey(long articleId) {
        return "article:" + articleId + ":name";
    }

    private String votesKey(long articleId) {
        return "article:" + articleId + ":votes";
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("article:101:name", "Article A");
        jedis.set("article:102:name", "Article B");
        jedis.set("article:103:name", "Article C");

        VotingService votingService = new VotingService(jedis);
        votingService.upVote(101);
        votingService.upVote(101);
        votingService.upVote(101);
        System.out.println(votingService.getArticleRating(101));

        votingService.downVote(101);
        votingService.downVote(101);
        System.out.println(votingService.getArticleRating(101));

        jedis.quit();
    }
}
