package payu.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import payu.infrastructure.exceptions.InsufficientFundsException;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountNumber")
    private Long number;

    @Column
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy= "account")
    @JsonManagedReference
    private List<Movement> movements;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @JsonBackReference
    private Client client;

    public Account(Client client) {
        super();
        this.balance = new BigDecimal(0.0);
        this.movements = new ArrayList();
        this.client = client;
    }

    /**
     * JPA required constructor
     */
    protected Account() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void applyMovement(Movement movement) throws InsufficientFundsException {
        if(movement.getType().equals( MovementType.CREDIT ) && movement.getValue().compareTo(balance) > 0 ) {
            throw new InsufficientFundsException(number);
        }
        switch (movement.getType()){
            case DEBIT:
                balance = balance.add(movement.getValue());
                return;
            case CREDIT:
                balance = balance.subtract(movement.getValue());
                return;
        }
    }

}
