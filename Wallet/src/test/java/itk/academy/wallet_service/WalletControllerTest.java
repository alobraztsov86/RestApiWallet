package itk.academy.wallet_service;

import itk.academy.wallet_service.controller.WalletController;
import itk.academy.wallet_service.dto.TransactionDto;
import itk.academy.wallet_service.entity.OperationType;
import itk.academy.wallet_service.entity.Transaction;
import itk.academy.wallet_service.mapper.TransactionMapper;
import itk.academy.wallet_service.mapper.WalletMapper;
import itk.academy.wallet_service.service.TransactionService;
import itk.academy.wallet_service.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {
    @Mock
    private TransactionService transactionService;
    @Mock
    private WalletService walletService;
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private WalletMapper walletMapper;

    @InjectMocks
    private TransactionDto transactionDtoValid;
    @InjectMocks
    private TransactionDto transactionDtoNotValid;
    @InjectMocks
    UUID uuid = UUID.randomUUID();

    @InjectMocks
    private WalletController walletController;

    @BeforeEach
    void setUp() {
        transactionDtoValid = new TransactionDto(UUID.randomUUID(), OperationType.DEPOSIT, 1);
        transactionDtoNotValid = new TransactionDto(null, OperationType.DEPOSIT, -1);
    }

    @Test
    @DisplayName("walletTransaction() invokes TransactionService.processTransaction()")
    public void testWalletTransactionMethodInvokesProcessTransaction() {
        walletController.walletTransaction(transactionDtoValid);
        Mockito.verify(transactionService, Mockito.times(1)).processTransaction(Mockito.any());
    }

    @Test
    @DisplayName("walletTransaction() invokes TransactionService.processTransaction() with correct data")
    public void testWalletTransactionMethodInvokesProcessTransactionWithCorrectData() {
        walletController.walletTransaction(transactionDtoValid);
        Transaction transaction = transactionMapper.toEntity(transactionDtoValid);
        Mockito.verify(transactionService, Mockito.times(1)).processTransaction(transaction);
    }

    @Test
    @DisplayName("walletTransaction() invokes WalletService.processTransaction()")
    public void testGetWalletBalanceMethodInvokesGetWallet() {
        walletController.getWalletBalance(uuid);
        Mockito.verify(walletService, Mockito.times(1)).getWallet(Mockito.any());
    }

    @Test
    @DisplayName("walletTransaction() invokes WalletService.processTransaction() with correct UUID")
    public void testGetWalletBalanceMethodInvokesGetWalletWithCorrectUuid() {
        walletController.getWalletBalance(uuid);
        Mockito.verify(walletService, Mockito.times(1)).getWallet(uuid);
    }

}
