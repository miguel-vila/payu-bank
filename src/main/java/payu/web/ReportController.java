package payu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import payu.infrastructure.data.AccountReport;
import payu.infrastructure.data.AccountReportParams;
import payu.infrastructure.exceptions.ClientDoesntExistException;
import payu.services.AccountService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/reports/client-accounts-by-date-range", method = RequestMethod.POST)
    @ResponseBody
    public List<AccountReport> getReport(@Valid @RequestBody AccountReportParams params) throws ClientDoesntExistException {
        return accountService.filterAccounts(params.getClientId(), params.getStart(), params.getEnd());
    }

}
