UPDATE
    account
SET
    fst_nm = ?,
    lst_nm = ?,
    email = ?,
    lst_updt_tm = NOW(),
    lst_updt_by_acct_key = ?
WHERE
    id = ?;