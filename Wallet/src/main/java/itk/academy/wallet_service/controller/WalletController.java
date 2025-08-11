package itk.academy.wallet_service.controller;

import itk.academy.wallet_service.dto.TransactionDto;
import itk.academy.wallet_service.dto.WalletDto;
import itk.academy.wallet_service.mapper.TransactionMapper;
import itk.academy.wallet_service.mapper.WalletMapper;
import itk.academy.wallet_service.service.TransactionService;
import itk.academy.wallet_service.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("api/v1")
public class WalletController {
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final WalletMapper walletMapper;

    @PostMapping("/wallet")
    @ResponseBody
    public void walletTransaction(@Validated @RequestBody TransactionDto transactionDto) {
        transactionService.processTransaction(transactionMapper.toEntity(transactionDto));
    }

    @GetMapping("/wallet/{WALLET_UUID}")
    public WalletDto getWalletBalance(@PathVariable UUID WALLET_UUID) {
        return walletMapper.toDto(walletService.getWallet(WALLET_UUID));
    }
}
