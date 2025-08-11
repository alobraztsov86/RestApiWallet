package itk.academy.wallet_service.exception;

public class BalanceLowException extends RuntimeException {
    public BalanceLowException(String message) {
        super(message);
    }
}
