CREATE TABLE IF NOT EXISTS `BOOKINGENTITY`(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `bookingType`  VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `BookingTypeDetails`(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `typeId`        VARCHAR(30) NOT NULL,
    `totalSeats`   INTEGER ,
    `availableSeats`  INTEGER ,
    `parentId`         INTEGER
);

CREATE TABLE IF NOT EXISTS `RowEntity`(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `rowId`        VARCHAR(30) NOT NULL,
    `parentId`         INTEGER
);

CREATE TABLE IF NOT EXISTS `SeatEntity`(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `seatNumber`        INTEGER NOT NULL,
    `isAvailable`        BOOLEAN,
   `parentId`         INTEGER
);


