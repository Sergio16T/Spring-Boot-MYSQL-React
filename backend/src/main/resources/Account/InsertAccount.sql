INSERT INTO
    account
(
    id,
    fst_nm,
    lst_nm,
    email,
    password_nm,
    crte_tm,
    crte_by_acct_key,
    act_ind
)
VALUES
(
    ?,
    ?,
    ?,
    ?,
    ?,
    NOW(),
    ?,
    ?
);