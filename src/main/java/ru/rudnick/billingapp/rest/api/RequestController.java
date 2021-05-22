package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Request;
import ru.rudnick.billingapp.repository.AuditRepository;
import ru.rudnick.billingapp.repository.RequestRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    AuditRepository auditRepository;

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable("id") Request request) {
        return request;
    }

    @PostMapping("/create")
    public Request createNewRequest(@RequestParam("accountFromId") Account accountFrom,
                                    @RequestParam("accountToId") Account accountTo,
                                    @RequestParam("amount") BigDecimal amount) {
        Request newRequest = new Request(accountFrom, accountTo, amount);
        Request request = requestRepository.save(newRequest);
        Audit audit = new Audit();
        audit.setRequest(request);
        auditRepository.save(audit);
        return request;
    }
}
