CREATE TABLE Storage (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Address TEXT NOT NULL
);

CREATE TABLE Dealer (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(255),
    Phone VARCHAR(50),
    Address TEXT NOT NULL
);

CREATE TABLE Product (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Weight NUMERIC(10, 2) NOT NULL,
    Volume NUMERIC(10, 2) NOT NULL,
    DealerID INTEGER,
    FOREIGN KEY (DealerID) REFERENCES Dealer(ID)
);

CREATE TABLE Sender (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(255),
    Phone VARCHAR(50),
    Address TEXT NOT NULL
);

CREATE TABLE Recipient (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(255),
    Phone VARCHAR(50),
    Address TEXT NOT NULL
);

CREATE TABLE Shipment (
    ID SERIAL PRIMARY KEY,
    ShipmentDate DATE NOT NULL,
    Quantity INTEGER NOT NULL,
    Cost NUMERIC(10, 2) NOT NULL,
    StorageID INTEGER,
    SenderID INTEGER,
    RecipientID INTEGER,
    FOREIGN KEY (StorageID) REFERENCES Storage(ID),
    FOREIGN KEY (SenderID) REFERENCES Sender(ID),
    FOREIGN KEY (RecipientID) REFERENCES Recipient(ID)
);

CREATE TABLE ProductInShipment (
    ShipmentID INTEGER,
    ProductID INTEGER,
    FOREIGN KEY (ShipmentID) REFERENCES Shipment(ID),
    FOREIGN KEY (ProductID) REFERENCES Product(ID),
    PRIMARY KEY (ShipmentID, ProductID)
);

CREATE TABLE ProductInStorage (
    ItemID INTEGER,
    WarehouseID INTEGER,
    DealerID INTEGER,
    Quantity INTEGER NOT NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ID),
    FOREIGN KEY (StorageID) REFERENCES Storage(ID),
    FOREIGN KEY (DealerID) REFERENCES Dealer(ID),
    PRIMARY KEY (ItemID, WarehouseID, DealerID)
);


