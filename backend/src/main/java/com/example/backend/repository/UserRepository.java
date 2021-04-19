package com.example.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper < User > {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setFirstName(resultSet.getString("FST_NM"));
            user.setLastName(resultSet.getString("LST_NM"));
            user.setEmail(resultSet.getString("EMAIL"));
            // More options in case we want to grab more values at a later date
            // user.setPassword(resultSet.getString("PASSWORD_NM"));
            // user.setResetToken(resultSet.getString("RESET_TOKEN"));
            // user.setResetTokenExpiration(resultSet.getString("RESET_TOKEN_EXPIRY"));
            // user.setCreateTime(resultSet.getTimestamp("CRTE_TM"));
            // user.setCreateByAccountKey(resultSet.getLong("CRTE_BY_ACCT_KEY"));
            // user.setLastUpdateTime(resultSet.getTimestamp("LST_UPDT_TM"));
            // user.setLastUpdateByAccountKey(resultSet.getLong("LST_UPDT_BY_ACCT_KEY"));
            // user.setActiveIndicator(resultSet.getInt("ACT_IND"));
            return user;
        }
    }

    public List < User > findAll() {
        return jdbcTemplate.query("SELECT id, fst_nm, lst_nm, email FROM user WHERE act_ind = 1", new UserRowMapper());
    }

    public Optional < User > findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT id, fst_nm as firstName, lst_nm as lastName, email as email from user where id = ?", new Object[] {
                id
            },
            new BeanPropertyRowMapper < User > (User.class)));
    }

    public Optional < User > checkIfEmailUnavailable(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT id, fst_nm as firstName, lst_nm as lastName, email as email from user where email = ?", new Object[] {
                email
            },
            new BeanPropertyRowMapper< User >(User.class)));
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Email Available");
            return Optional.empty();
        }
    }
    public Optional < User > findByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT id, fst_nm as firstName, lst_nm as lastName, email as email, password_nm as password from user where email= ?", new Object[] {
            email
        },
        new BeanPropertyRowMapper< User >(User.class)));
    }

    public int delete(long id) {
        return jdbcTemplate.update("delete from user where id = ?", new Object[] {
            id
        });
    }

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO user (id, fst_nm, lst_nm, email, password_nm, crte_tm, crte_by_acct_key, act_ind) " + "values(?, ?, ?, ?, ?, NOW(), ?, ?)",
            new Object[] {
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), 1, 1
            });
    }

    public int update(User user) {
        return jdbcTemplate.update("update user " + " set fst_nm = ?, lst_nm = ?, email = ?, lst_updt_tm = NOW(), lst_updt_by_acct_key = ?" + " where id = ?",
            new Object[] {
                user.getFirstName(), user.getLastName(), user.getEmail(), 1, user.getId()
            });
    }
}