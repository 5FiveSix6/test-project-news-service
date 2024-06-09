package yuriy.spring.news_service.exception;

public class NewsAlreadyPublishedException extends RuntimeException {

    public NewsAlreadyPublishedException(String message) {
        super(message);
    }
}
