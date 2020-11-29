package app.dao;
import app.model.Customer;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CollectionCustomerDao implements Dao<Customer> {

    private final List<Customer> customers;
    private long idCounter;

    public CollectionCustomerDao() {
        this.customers = new ArrayList<>();
        this.idCounter = 1;
    }
    @Override
    public Customer save(Customer customer) {
        if(customer.getId() == null) {
            customer.setId(idCounter++);
        } else {
            this.deleteById(customer.getId());
        }
        this.customers.add(customer);
        return customer;
    }
    @Override
    public boolean delete(Customer customer) {
        return this.customers.remove(customer);
    }
    @Override
    public void deleteAll(List<Customer> customers) {
        this.customers.removeAll(customers);
    }
    @Override
    public void saveAll(List<Customer> customers) {
        customers.forEach(cus->{
            if(cus.getId()== null){
                cus.setId(idCounter++);
            } else {
                this.deleteById(cus.getId());
            }
            this.customers.add(cus);
        });
    }
    @Override
    public List<Customer> findAll() {
        return this.customers;
    }

    @Override
    public boolean deleteById(long id) {
        return customers.removeIf(el-> el.getId().equals(id));
    }
    @Override
    public Customer getOne(long id) {
        return customers.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
