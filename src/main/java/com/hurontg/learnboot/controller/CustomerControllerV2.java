package com.hurontg.learnboot.controller;

import com.hurontg.learnboot.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
//@RequestMapping("/customers")
public class CustomerControllerV2 {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @GetMapping()
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = jdbcTemplate.query(
//                        "SELECT id, firstname, lastname FROM customer",
//                        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"))
//                );
//
//        return customers;
//    }
//
//    @PostMapping()
//    public void createCustomer(@RequestBody Customer customer) {
//        System.out.println(customer);
//
//        jdbcTemplate.update("INSERT INTO customer (firstname, lastname) VALUES(?,?)",
//                new Object[] { customer.getFirstName(), customer.getLastName() });
//
//    }
//
//    @PutMapping("/{id}")
//    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//        System.out.println(customer + " id: " + id );
//
//        jdbcTemplate.update("UPDATE customer SET firstname = ?, lastname = ? WHERE id = ?",
//                new Object[] { customer.getFirstName(), customer.getLastName(), id });
//
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void deleteCustomer(@PathVariable Long id) {
//        jdbcTemplate.update("DELETE FROM customer WHERE id = ?", new Object[] { id });
//    }
}