CREATE TABLE Document (
  id serial NOT NULL,
  title text NOT NULL,
  link text NOT NULL,
  PRIMARY KEY (id)
);

CREATE TYPE UserPosition AS ENUM
(
    'ADMIN',
    'DIRECTOR',
    'DEPARTMENT_SPECIALIST',
    'DEPARTMENT_HEAD',
	'CLIENT'
);


CREATE TABLE Role (
  id serial NOT NULL,
  name UserPosition NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Users (
  id serial NOT NULL,
  username text NOT NULL,
  email text NOT NULL,
  password text NOT NULL,
  roleId int NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT Users_roleId_Role_id_foreign FOREIGN KEY (roleId) REFERENCES Role (id),
  CONSTRAINT "unique_email" UNIQUE (email),
  CONSTRAINT "unique_username" UNIQUE (username)
);

CREATE TYPE AssignmentStatus AS ENUM
(
    'CONFIRMED',
    'NOT_CONFIRMED'
);

CREATE TABLE Assignment (
  id serial NOT NULL,
  status AssignmentStatus NOT NULL,
  description text NOT NULL,
  senderRoleId integer NOT NULL,
  recipientRoleId integer NOT NULL,
  documentId integer NULL,
  PRIMARY KEY (id),
  CONSTRAINT Assignment_senderRoleId_Role_id_foreign FOREIGN KEY (senderRoleId) REFERENCES Role (id),
  CONSTRAINT Assignment_recipientRoleId_Role_id_foreign FOREIGN KEY (recipientRoleId) REFERENCES Role (id),
  CONSTRAINT Assignment_documentId_Role_id_foreign FOREIGN KEY (documentId) REFERENCES Document (id)
);