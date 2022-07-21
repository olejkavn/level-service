create table if not exists users (
                                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                     user_id INT NOT NULL UNIQUE,
                                     level INT NOT NULL,
                                     exp INT NOT NULL,
                                     PRIMARY KEY(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;