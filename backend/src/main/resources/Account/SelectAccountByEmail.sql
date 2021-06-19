SELECT
    id,
    fst_nm as firstName,
    lst_nm as lastName,
    email,
    password_nm as password
FROM
    account
WHERE
    email = ?;