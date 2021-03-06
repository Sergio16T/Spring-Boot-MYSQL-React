CREATE TABLE IF NOT EXISTS account (
    ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FST_NM VARCHAR(100) NOT NULL,
    LST_NM VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PASSWORD_NM VARCHAR(100),
    RESET_TOKEN VARCHAR(100),
    RESET_TOKEN_EXPIRY VARCHAR(255),
    CRTE_TM DATETIME,
    CRTE_BY_ACCT_KEY BIGINT UNSIGNED,
    LST_UPDT_TM DATETIME,
    LST_UPDT_BY_ACCT_KEY BIGINT UNSIGNED,
    ACT_IND BOOLEAN NOT NULL
)

CREATE TABLE IF NOT EXISTS rental_property (
    ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
)