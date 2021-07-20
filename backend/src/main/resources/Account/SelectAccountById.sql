SELECT
    id,
    fst_nm as firstName,
    lst_nm as lastName,
    email,
    crte_tm as createTime
FROM
    account
WHERE
    id = ?;