package itk.academy.wallet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletServiceApplication {
    public static void main(String[] args) {
        System.out.println("Успешный запуск !!!");
        System.out.println("Приложение готово к обработке запросов !!!");
        SpringApplication.run(WalletServiceApplication.class, args);
    }
}