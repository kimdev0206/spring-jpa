package study.spring_jpa.exception;

public class NotEnoughQuantityException extends RuntimeException {

  public NotEnoughQuantityException(String message) {
    super(message);
  }
}
