package com.example.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.backend.model.Account;
import com.example.backend.utilities.ReadSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class AccountRowMapper implements RowMapper <Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getLong("ID"));
            account.setFirstName(resultSet.getString("FST_NM"));
            account.setLastName(resultSet.getString("LST_NM"));
            account.setEmail(resultSet.getString("EMAIL"));
            // More options in case we want to grab more values at a later date
            // account.setPassword(resultSet.getString("PASSWORD_NM"));
            // account.setResetToken(resultSet.getString("RESET_TOKEN"));
            // account.setResetTokenExpiration(resultSet.getString("RESET_TOKEN_EXPIRY"));
            // account.setCreateTime(resultSet.getTimestamp("CRTE_TM"));
            // account.setCreateByAccountKey(resultSet.getLong("CRTE_BY_ACCT_KEY"));
            // account.setLastUpdateTime(resultSet.getTimestamp("LST_UPDT_TM"));
            // account.setLastUpdateByAccountKey(resultSet.getLong("LST_UPDT_BY_ACCT_KEY"));
            // account.setActiveIndicator(resultSet.getInt("ACT_IND"));
            return account;
        }
    }

    public List <Account> findAll() {
        ReadSQL readSQL = new ReadSQL("/Account/SelectAllAccounts.sql");
        String query = readSQL.getQuery();
        return jdbcTemplate.query(query, new AccountRowMapper());
    }

    public Optional <Account> findById(long id) {
        ReadSQL readSQL = new ReadSQL("/Account/SelectAccountById.sql");
        String query = readSQL.getQuery();
        return Optional.of(jdbcTemplate.queryForObject(query, new Object[] {
                id
            },
            new BeanPropertyRowMapper <Account> (Account.class)));
    }

    public Optional <Account> checkIfEmailUnavailable(String email) {
        ReadSQL readSQL = new ReadSQL("/Account/SelectAccountByEmail.sql");
        String query = readSQL.getQuery();
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] {
                email
            },
            new BeanPropertyRowMapper<Account>(Account.class)));
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Email Available");
            return Optional.empty();
        }
    }
    public Optional <Account> findByEmail(String email) {
        ReadSQL readSQL = new ReadSQL("/Account/SelectAccountByEmail.sql");
        String query = readSQL.getQuery();
        return Optional.of(jdbcTemplate.queryForObject(query, new Object[] {
            email
        },
        new BeanPropertyRowMapper<Account>(Account.class)));
    }

    public int delete(long id) {
        ReadSQL readSQL = new ReadSQL("/Account/DeleteAccountById.sql");
        String query = readSQL.getQuery();
        return jdbcTemplate.update(query, new Object[] {
            id
        });
    }

    public int save(Account account) {
        ReadSQL readSQL = new ReadSQL("/Account/InsertAccount.sql");
        String query = readSQL.getQuery();
        return jdbcTemplate.update(query,
            new Object[] {
                account.getId(), account.getFirstName(), account.getLastName(), account.getEmail(), account.getPassword(), 1, 1
            });
    }

    public int update(Account account) {
        ReadSQL readSQL = new ReadSQL("/Account/UpdateAccount.sql");
        String query = readSQL.getQuery();
        return jdbcTemplate.update(query,
            new Object[] {
                account.getFirstName(), account.getLastName(), account.getEmail(), 1, account.getId()
            });
    }
}