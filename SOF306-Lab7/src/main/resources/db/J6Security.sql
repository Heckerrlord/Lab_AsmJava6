USE master
GO
CREATE DATABASE J6Security
GO
USE J6Security
GO
CREATE TABLE Accounts (
	Username varchar(20) NOT NULL,
	Password varchar(100) NULL,
	Fullname nvarchar(50) NULL,
	Email varchar(50) NULL,
	Photo nvarchar(50) NULL,
	CONSTRAINT PK_Accounts PRIMARY KEY (Username)
)
GO
CREATE TABLE Authorities (
	Id int IDENTITY(1,1) NOT NULL,
	Username varchar(20) NOT NULL,
	RoleId varchar(10) NOT NULL,
	CONSTRAINT PK_UserRoles PRIMARY KEY (Id)
)
GO
CREATE TABLE Roles (
	Id varchar(10) NOT NULL,
	Name nvarchar(50) NULL,
	CONSTRAINT PK_Roles PRIMARY KEY (Id)
)
GO
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo) VALUES ('user1', '123', N'User 1', 'user1@fpt.edu.vn',NULL)
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo) VALUES ('user2', '123', N'User 2', 'user2@fpt.edu.vn',NULL)
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo) VALUES ('user3', '123', N'User 3', 'user3@fpt.edu.vn',NULL)
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo) VALUES ('user4', '123', N'User 4', 'user4@fpt.edu.vn',NULL)
GO
SET IDENTITY_INSERT Authorities ON
INSERT INTO Authorities (Id, Username, RoleId) VALUES (1, 'user1', 'GUEST')
INSERT INTO Authorities (Id, Username, RoleId) VALUES (2, 'user2', 'USER')
INSERT INTO Authorities (Id, Username, RoleId) VALUES (3, 'user3', 'ADMIN')
SET IDENTITY_INSERT Authorities OFF
GO
INSERT INTO Roles (Id, Name) VALUES ('ADMIN', N'Administrators')
INSERT INTO Roles (Id, Name) VALUES ('USER', N'Users')
INSERT INTO Roles (Id, Name) VALUES ('GUEST', N'Guests')
GO
ALTER TABLE Authorities WITH CHECK ADD CONSTRAINT FK_Authorities_Roles FOREIGN KEY (RoleId)
REFERENCES Roles(Id) ON UPDATE CASCADE ON DELETE CASCADE
GO
ALTER TABLE Authorities CHECK CONSTRAINT FK_Authorities_Roles
GO
ALTER TABLE Authorities WITH CHECK ADD CONSTRAINT FK_Authorities_Accounts FOREIGN KEY (Username)
REFERENCES Accounts(Username) ON UPDATE CASCADE ON DELETE CASCADE
GO
ALTER TABLE Authorities CHECK CONSTRAINT FK_Authorities_Accounts
GO