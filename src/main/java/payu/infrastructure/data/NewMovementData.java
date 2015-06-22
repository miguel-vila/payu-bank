package payu.infrastructure.data;

import payu.infrastructure.exceptions.InvalidMovementTypeException;
import payu.models.Movement;
import payu.models.MovementType;

import java.math.BigDecimal;
import java.util.Date;

public class NewMovementData {

    private BigDecimal ammount;

    private String type;

    public NewMovementData(){}

    public NewMovementData(BigDecimal ammount, String type){
        this.ammount = ammount;
        this.type = type;
    }

    public Movement toMovement() throws InvalidMovementTypeException {
        try {
            MovementType _type = MovementType.valueOf(type);
            return new Movement(_type, new Date(), ammount);
        } catch (Exception e) {
            throw new InvalidMovementTypeException(type);
        }
    }

    public BigDecimal getAmmount(){
        return ammount;
    }

    public String getType(){
        return type;
    }

    public void setAmmount(BigDecimal ammount){
        this.ammount = ammount;
    }

    public void setType(String type){
        this.type = type;
    }

}
