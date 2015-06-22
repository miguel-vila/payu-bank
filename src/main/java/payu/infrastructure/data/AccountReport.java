package payu.infrastructure.data;

import payu.models.Account;
import payu.models.Movement;
import payu.models.MovementType;
import payu.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mglvl on 21/06/15.
 */
public class AccountReport {

    private Long accountNumber;

    private BigDecimal balance;

    private List<Movement> creditMovements;

    private List<Movement> debitMovements;

    public AccountReport(){
    }

    public List<Movement> getCreditMovements() {
        return creditMovements;
    }

    public void setCreditMovements(List<Movement> creditMovements) {
        this.creditMovements = creditMovements;
    }

    public List<Movement> getDebitMovements() {
        return debitMovements;
    }

    public void setDebitMovements(List<Movement> debitMovements) {
        this.debitMovements = debitMovements;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static AccountReport fromAccount(Account account, Date start, Date end){
        AccountReport report = new AccountReport();

        List<Movement> filteredMovements = account
                .getMovements()
                .stream()
                .filter(mov -> DateUtils.isInBetween(mov.getDate(), start, end))
                .collect(Collectors.toList());

        List<Movement> credits = filteredMovements.stream().filter(mov -> mov.getType().equals(MovementType.CREDIT)).collect(Collectors.toList());
        List<Movement> debits = filteredMovements.stream().filter(mov -> mov.getType().equals(MovementType.DEBIT)).collect(Collectors.toList());

        report.setCreditMovements(credits);
        report.setDebitMovements(debits);
        report.setAccountNumber(account.getNumber());
        report.setBalance(account.getBalance());

        return report;
    }
}
