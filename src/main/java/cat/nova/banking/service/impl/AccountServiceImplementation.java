package cat.nova.banking.service.impl;

import cat.nova.banking.dto.AccountDto;
import cat.nova.banking.entity.Account;
import cat.nova.banking.mapper.AccountMapper;
import cat.nova.banking.repository.AccountRepository;
import cat.nova.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
       Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("No account with id " + accountId));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with id " + id));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with id " + id));
        if(account.getBalance()<amount) throw new RuntimeException("Insufficient balance");
        double amountAfterWithdraw = account.getBalance()-amount;
        account.setBalance(amountAfterWithdraw);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountsDto = accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return accountsDto;
    }

    @Override
    public void deleteAccount(long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("No account with id " + accountId));
        accountRepository.deleteById(accountId);
    }
}
