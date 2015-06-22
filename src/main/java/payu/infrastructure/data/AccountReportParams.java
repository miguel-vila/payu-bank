package payu.infrastructure.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import payu.infrastructure.serialization.JsonDateDeserializer;

import java.util.Date;

public class AccountReportParams {

    private Long clientId;

    private Date start;

    private Date end;

    public AccountReportParams(){}

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
