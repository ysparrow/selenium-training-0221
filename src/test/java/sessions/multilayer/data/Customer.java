package sessions.multilayer.data;

public class Customer {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private String zone;
    private String email;
    private String phone;
    private String password;

    public static Builder newEntity() {
        return new Customer().new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZone() {
        return zone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public class Builder {
        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            Customer.this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            Customer.this.lastName = lastName;
            return this;
        }

        public Builder withAddress(String address) {
            Customer.this.address = address;
            return this;
        }

        public Builder withPostcode(String postcode) {
            Customer.this.postcode = postcode;
            return this;
        }

        public Builder withCity(String city) {
            Customer.this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            Customer.this.country = country;
            return this;
        }

        public Builder withZone(String zone) {
            Customer.this.zone = zone;
            return this;
        }

        public Builder withEmail(String email) {
            Customer.this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            Customer.this.phone = phone;
            return this;
        }

        public Builder withPassword(String password) {
            Customer.this.password = password;
            return this;
        }

        public Customer build() {
            return Customer.this;
        }
    }
}