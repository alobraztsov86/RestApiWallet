package itk.academy.wallet_service.service;

import itk.academy.wallet_service.entity.OperationStatus;
import itk.academy.wallet_service.entity.OperationType;
import itk.academy.wallet_service.entity.Transaction;
import itk.academy.wallet_service.exception.BalanceLowException;
import itk.academy.wallet_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;

    @Transactional
    public void processTransaction(Transaction transaction){
//        transactionRepository.save(transaction);
        BigDecimal balance = walletService.getWallet(transaction.getWallet().getId()).getAmount();
        if (transactionIsPossible(balance, transaction)) {
            walletService.updateWalletAmount(transaction.getWallet().getId(),
                    transaction.getOperationType(),
                    transaction.getAmount());
            transaction.setStatus(OperationStatus.COMPLETED);
            transactionRepository.save(transaction);
            log.info("Transaction is processed successfully: {}", transaction);
        } else {
            transaction.setStatus(OperationStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BalanceLowException("Transaction failed due to low balance: " + balance);
        }
    }

    private boolean transactionIsPossible(BigDecimal balance, Transaction transaction) {
        if (transaction.getOperationType() == OperationType.DEPOSIT) {
            return true;
        }
        if(transaction.getOperationType() == OperationType.WITHDRAW
                && (balance.compareTo(transaction.getAmount()) > 0)) {
            return true;
        }
        return false;
    }
}
