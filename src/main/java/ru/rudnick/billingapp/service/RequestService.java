package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Request;
import ru.rudnick.billingapp.repository.AccountRepository;
import ru.rudnick.billingapp.repository.AuditRepository;
import ru.rudnick.billingapp.repository.BillRepository;
import ru.rudnick.billingapp.repository.RequestRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    AuditRepository auditRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Bill auditResolution(Request request, Request.Status status) {
        switch (status) {
            case ACCEPT:
                Account accountFrom = request.getFrom();
                Account accountTo = request.getTo();
                BigDecimal amount = request.getAmount();
                Bill newBill = new Bill(accountFrom, accountTo, amount, request);
                request.setStatus(status);
                requestRepository.save(request);
                Bill bill = billRepository.save(newBill);
                accountFrom.setAmount(accountFrom.getAmount().subtract(amount));
                accountTo.setAmount(accountTo.getAmount().add(amount));
                accountRepository.saveAll(Arrays.asList(accountFrom, accountTo));
                return bill;
            case REJECT:
                request.setStatus(status);
                requestRepository.save(request);
                return null;
            default:
                return null;
        }
    }


    public Request createNewRequest(@RequestParam("accountFromId") Account accountFrom,
                                    @RequestParam("accountToId") Account accountTo,
                                    @RequestParam("amount") BigDecimal amount) {
        if (accountFrom.getAccountId().equals(accountTo.getAccountId())) {
            return null;
        }
        Request newRequest = new Request(accountFrom, accountTo, amount);
        Request request = requestRepository.save(newRequest);
        Audit audit = new Audit();
        audit.setRequest(request);
        auditRepository.save(audit);
        return request;
    }


}
