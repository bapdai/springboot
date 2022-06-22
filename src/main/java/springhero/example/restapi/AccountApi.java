package springhero.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springhero.example.entity.Account;
import springhero.example.service.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountApi {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getList() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalAccount.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> create(@RequestBody Account product) {
        return ResponseEntity.ok(accountService.save(product));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Account existAccount = optionalAccount.get();
        // map object
        existAccount.setFirstName(account.getFirstName());
        existAccount.setLastName(account.getLastName());
        return ResponseEntity.ok(accountService.save(existAccount));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!accountService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        accountService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

