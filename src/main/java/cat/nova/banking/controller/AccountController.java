package cat.nova.banking.controller;

import cat.nova.banking.dto.AccountDto;
import cat.nova.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositMoney(@PathVariable long id,@RequestBody Map<String,Double> request){

        return new ResponseEntity<>(accountService.deposit(id,request.get("amount")),HttpStatus.OK);

    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable long id,@RequestBody Map<String,Double> request){

        return new ResponseEntity<>(accountService.withdraw(id,request.get("amount")),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted",HttpStatus.OK);
    }
}
