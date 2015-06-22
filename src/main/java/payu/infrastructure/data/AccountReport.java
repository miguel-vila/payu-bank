package payu.infrastructure.data;

import payu.models.Account;
import payu.models.Movement;
import payu.models.MovementType;
import payu.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mglvl on 21/06/15.
 */
public class AccountReport {

    private List<Movement> creditMovements;

    private List<Movement> debitMovements;

    public AccountReport(){
    }

    public AccountReport(
            List<Movement> creditMovements,
            List<Movement> debitMovements
    ) {
        this.creditMovements = creditMovements;
        this.debitMovements = debitMovements;
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

    public static AccountReport fromAccount(Account account, Date start, Date end){
        AccountReport report = new AccountReport();

        Stream<Movement> filteredMovements = account
                .getMovements()
                .stream()
                .filter(mov -> DateUtils.isInBetween(mov.getDate(), start, end));

        List<Movement> credits = filteredMovements.filter(mov -> mov.getType().equals(MovementType.CREDIT)).collect(Collectors.toList());
        List<Movement> debits = filteredMovements.filter(mov -> mov.getType().equals(MovementType.DEBIT)).collect(Collectors.toList());

        report.setCreditMovements(credits);
        report.setDebitMovements(debits);

        return report;
    }
}
