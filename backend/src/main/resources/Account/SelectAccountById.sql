SELECT
    id,
    fst_nm as firstName,
    lst_nm as lastName,
    email
FROM
    account
WHERE
    id = ?;