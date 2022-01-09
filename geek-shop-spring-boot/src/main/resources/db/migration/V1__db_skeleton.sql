CREATE TABLE LoyaltyCards (
    LoyaltyCardNumber bigint NOT NULL,
    BonusPoints smallint NOT NULL,
    PRIMARY KEY (LoyaltyCardNumber)
);

CREATE TABLE Persons (
    PersonId bigserial NOT NULL,
    FullName text NOT NULL,
    DateOfBirth text NOT NULL,
    Gender char(1) NOT NULL,
    Email text DEFAULT NULL UNIQUE,
    PhoneNumber bigint NOT NULL UNIQUE,
    PRIMARY KEY (PersonId),
    CONSTRAINT gender_check CHECK (
        Gender = 'f'
        OR Gender = 'm'
    )
);

CREATE TABLE Universes (
    UniverseId bigserial NOT NULL,
    UniverseName text NOT NULL UNIQUE,
    BriefInformation text NOT NULL,
    PRIMARY KEY (UniverseId)
);

CREATE TABLE Clients (
    ClientId bigserial NOT NULL,
    LoyaltyCardNumber bigint DEFAULT NULL UNIQUE,
    PRIMARY KEY (ClientId),
    CONSTRAINT fk_Clients_ClientId_Persons_PersonId FOREIGN KEY (ClientId) REFERENCES Persons (PersonId),
    CONSTRAINT fk_Clients_LoyaltyCardNumber_LoyaltyCards_LoyaltyCardNumber FOREIGN KEY (LoyaltyCardNumber) REFERENCES LoyaltyCards (LoyaltyCardNumber)
);

CREATE TABLE EmployeePositions (
    PositionId bigserial NOT NULL,
    PositionName text NOT NULL,
    PRIMARY KEY (PositionId)
);

CREATE TABLE Employees (
    EmployeeId bigserial NOT NULL,
    PositionId integer NOT NULL,
    Salary money NOT NULL,
    EmploymentDate date NOT NULL,
    PRIMARY KEY (EmployeeId),
    CONSTRAINT fk_Employees_PositionId_EmployeePositions_PositionId FOREIGN KEY (PositionId) REFERENCES EmployeePositions (PositionId),
    CONSTRAINT fk_Employees_EmployeeId_Persons_PersonId FOREIGN KEY (EmployeeId) REFERENCES Persons (PersonId)
);

CREATE TABLE Reviews (
    ReviewId bigserial NOT NULL,
    ReviewType char(8) NOT NULL,
    TextOfReview text NOT NULL,
    ReviewDate date NOT NULL,
    ClientId serial NOT NULL,
    ReviewName text NOT NULL,
    PRIMARY KEY (ReviewId),
    CONSTRAINT fk_Reviews_ClientId_Clients_ClientId FOREIGN KEY (ClientId) REFERENCES Clients (ClientId),
    CONSTRAINT review_type_check CHECK (
        ReviewType = 'positive'
        OR ReviewType = 'negative'
    )
);

CREATE TABLE SanitaryWorks (
    SanitaryWorkId bigserial NOT NULL,
    TypeOfCleaning text NOT NULL,
    EmployeeId integer NOT NULL,
    DateAndTime timestamp NOT NULL,
    PRIMARY KEY (SanitaryWorkId),
    CONSTRAINT fk_SanitaryWorks_EmployeeId_Employees_EmployeeId FOREIGN KEY (EmployeeId) REFERENCES Employees (EmployeeId)
);

CREATE TABLE ProductTypes (
    ProductTypeId bigserial NOT NULL,
    ProductTypeName text NOT NULL,
    PRIMARY KEY (ProductTypeId)
);

CREATE TABLE Products (
    ProductId bigserial NOT NULL,
    ProductTypeId integer NOT NULL,
    Information text NOT NULL,
    Price money NOT NULL,
    Name text NOT NULL,
    UniverseId integer NOT NULL,
    PRIMARY KEY (ProductId),
    CONSTRAINT fk_Products_UniverseId_Universes_UniverseId FOREIGN KEY (UniverseId) REFERENCES Universes (UniverseId),
    CONSTRAINT fk_Products_ProductTypeId_ProductTypes_ProductTypeId FOREIGN KEY (ProductTypeId) REFERENCES ProductTypes (ProductTypeId)
);

CREATE TABLE Orders (
    OrderId bigserial NOT NULL,
    ClientId integer NOT NULL,
    ProductId integer NOT NULL,
    OrderType char(7) NOT NULL,
    PRIMARY KEY (OrderId),
    CONSTRAINT fk_Orders_ClientId_Clients_ClientId FOREIGN KEY (ClientId) REFERENCES Clients (ClientId),
    CONSTRAINT fk_Orders_ProductId_Products_ProductId FOREIGN KEY (ProductId) REFERENCES Products (ProductId),
    CONSTRAINT order_type_check CHECK (
            OrderType = 'online'
            OR OrderType = 'in shop'
        )
);