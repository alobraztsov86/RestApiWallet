package itk.academy.wallet_service.service;

import itk.academy.wallet_service.entity.OperationType;
import itk.academy.wallet_service.entity.Wallet;
import itk.academy.wallet_service.exception.DataValidationException;
import itk.academy.wallet_service.exception.NotFoundException;
import itk.academy.wallet_service.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;
    private final String ERROR_MSG_WALLET_NOT_FOUND = "walletId {} is not found";

    @Transactional
    public Wallet getWallet(UUID uuid) {
        Optional<Wallet> optional = walletRepository.findById(uuid);
        if (optional.isPresent()) {
            return optional.get();
        }
        log.error(ERROR_MSG_WALLET_NOT_FOUND, uuid);
        throw new DataValidationException(ERROR_MSG_WALLET_NOT_FOUND);
    }

    @Transactional
    public void updateWalletAmount(UUID uuid, OperationType operationType, BigDecimal value) {
        Wallet wallet = getWallet(uuid);
        BigDecimal newAmount;
        switch (operationType) {
            case DEPOSIT:
                newAmount = wallet.getAmount().add(value);
                break;
            case WITHDRAW:
                newAmount = wallet.getAmount().subtract(value);
                break;
            default: {
                throw new NotFoundException("OperationType is not supported: " + operationType);
            }
        }
        wallet.setAmount(newAmount);
        walletRepository.save(wallet);

    }
}
