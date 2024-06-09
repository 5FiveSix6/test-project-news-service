package yuriy.spring.news_service.exception;

public class InvalidUserRoleException extends RuntimeException {

    public InvalidUserRoleException(String message) {
        super(message);
    }
}
