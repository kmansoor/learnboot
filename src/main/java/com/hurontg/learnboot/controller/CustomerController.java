package com.hurontg.learnboot.controller;

import com.hurontg.learnboot.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {

        List<Customer> customers = jdbcTemplate.query(
                        "SELECT id, firstname, lastname FROM customer",
                        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"))
                );

        return customers;
    }

    @PostMapping("/customers")
    public void createCustomer(@RequestBody Customer customer) {
        System.out.println(customer);

        jdbcTemplate.update("INSERT INTO customer (firstname, lastname) VALUES(?,?)",
                new Object[] { customer.getFirstName(), customer.getLastName() });

        String sql = "INSERT INTO `user`(`balance`, `create_at`, `enabled`, `name`, `update_at`) VALUES(?, ?, ?, ?, ?);";
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        System.out.println(customer + " id: " + id );

        jdbcTemplate.update("UPDATE customer SET firstname = ?, lastname = ? WHERE id = ?",
                new Object[] { customer.getFirstName(), customer.getLastName(), id });

    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        jdbcTemplate.update("DELETE FROM customer WHERE id = ?", new Object[] { id });
    }

    @PostMapping("/customers/return-id")
    public Customer createCustomerAndReturnGeneratedKey(@RequestBody Customer customer) {
        final String sql = "INSERT INTO customer (firstname, lastname) VALUES(?,?) returning id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());

            return preparedStatement;

        }, keyHolder);

        Integer id = keyHolder.getKey().intValue();
        log.info(String.format(">>>ID: %d", id));

        customer.setId(id);
        return customer;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    @PostMapping("/customers/return-id")
//    public String test(HttpServletRequest request) {
//
//        return "test";
//    }

}