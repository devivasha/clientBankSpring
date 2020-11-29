package app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private List<Account> accounts;

    public Customer(Long id, String name, String email, Integer age, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.accounts = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(accounts, customer.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, accounts);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", accounts=" + accounts +
                '}';
    }
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
