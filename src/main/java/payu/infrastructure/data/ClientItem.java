package payu.infrastructure.data;

import payu.models.Client;

public class ClientItem {

    private Long id;
    private String name;
    private String address;
    private String phone;

    public ClientItem(Client client){
        this(client.getId(), client.getName(), client.getAddress(), client.getPhone());
    }

    public ClientItem(
            Long id,
            String name,
            String address,
            String phone
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}