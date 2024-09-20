package cat.nova.banking.service;

import cat.nova.banking.dto.AccountDto;
import cat.nova.banking.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountId);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(long accountId);

}
