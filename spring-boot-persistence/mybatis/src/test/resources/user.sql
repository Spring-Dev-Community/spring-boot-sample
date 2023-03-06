CREATE TABLE `db_user`
(
    `id`       varchar(36)  NOT NULL,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `db_user`(`id`,`username`,`password`) VALUES (1,'admin','123456');
INSERT INTO `db_user`(`id`,`username`,`password`) VALUES (2,'operator','123456');
INSERT INTO `db_user`(`id`,`username`,`password`) VALUES (3,'useradmin','123456');
INSERT INTO `db_user`(`id`,`username`,`password`) VALUES (4,'auditor','123456');
