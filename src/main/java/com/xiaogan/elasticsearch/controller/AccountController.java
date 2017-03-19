package com.xiaogan.elasticsearch.controller;


import com.xiaogan.elasticsearch.domain.Account;
import com.xiaogan.elasticsearch.service.AccountService;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/account")
public class AccountController {

    public final static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/response/{id}", method = RequestMethod.GET)
    public SearchResponse search(@PathVariable("id") String id) {
        return accountService.search(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public SearchResponse findAll() {
        return accountService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String find(@PathVariable String id) {
        String logString = accountService.find(id);
        return logString;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account insert(@RequestBody Account account) {
        if (account == null) {
            return null;
        }
        accountService.insert(account);
        return account;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Account update(@PathVariable String id, @RequestBody Account account) {
        if (account == null) {
            return null;
        }
        accountService.update(id, account);
        return account;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id) {
         return accountService.delete(id);
    }



}
