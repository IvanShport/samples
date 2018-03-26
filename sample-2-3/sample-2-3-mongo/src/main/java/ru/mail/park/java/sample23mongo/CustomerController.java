package ru.mail.park.java.sample23mongo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoWriteException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    String save(@RequestBody Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @ExceptionHandler(MongoWriteException.class)
    ResponseEntity<String> writeFailed(MongoWriteException mwe) {
        if (mwe.getCode() == 11000) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Customer already exists");
        } else {
            throw mwe;
        }
    }

    @GetMapping
    List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/name/{firstName}")
    ResponseEntity<Customer> getByFirstName(@PathVariable String firstName) {
        Customer customer = customerRepository.findByFirstName(firstName);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{lastName}")
    List<Customer> getByLastName(@PathVariable String lastName) {
        return customerRepository.findByLastName(lastName);
    }

}
