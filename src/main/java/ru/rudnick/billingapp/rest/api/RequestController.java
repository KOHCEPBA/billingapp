package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Request;
import ru.rudnick.billingapp.service.RequestService;
import ru.rudnick.billingapp.util.exception.InvalidRequest;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable("id") Request request) {
        return request;
    }

    @PostMapping("/create")
    public Request createNewRequest(@RequestParam("accountFromId") Account accountFrom,
                                    @RequestParam("accountToId") Account accountTo,
                                    @RequestParam("amount") BigDecimal amount) {
        Request request = requestService.createNewRequest(accountFrom, accountTo, amount);
        if (request != null) {
            return request;
        } else {
            throw new InvalidRequest("Can't create request with this accounts");
        }
    }

    @PostMapping("/{id}/{status}")
    public Bill auditResolution(@PathVariable("id") Request request, @PathVariable("status") Request.Status status) {
        if (request.getStatus() != Request.Status.OPEN) {
            throw new InvalidRequest("Can't change resolution");
        } else {
            return requestService.auditResolution(request, status);
        }
    }
}
