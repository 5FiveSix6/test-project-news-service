package yuriy.spring.news_service.exception;

public class InvalidModeratorIdException extends RuntimeException {

    public InvalidModeratorIdException(String message) {
        super(message);
    }
}
