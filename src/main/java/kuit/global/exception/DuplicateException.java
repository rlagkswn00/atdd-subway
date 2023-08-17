package kuit.global.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException() {
        super("이미 생성되어 있는 데이터입니다.");
    }

    public DuplicateException(String message) {
        super(message);
    }
}
