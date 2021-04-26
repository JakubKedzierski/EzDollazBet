CREATE PROCEDURE add_client

@Login varchar(255) = NULL,
@Password varchar(255) = NULL,
@FirstName varchar(255) = NULL,
@Surname varchar(255) = NULL,
@Age int(10) = NULL,
@Balance float(10) = 0.00

AS

DECLARE @err AS varchar(200);
IF @Login IS NULL OR @Password IS NULL OR @FirstName IS NULL OR
@Surname IS NULL OR @Age IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END
ELSE IF @Age < 18
BEGIN
     SET @err = 'Client is minor!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO clients(Login, FirstName, Surname, Age, Balance)
VALUES (@Login, @FirstName, @Surname, @Age, @Balance);

INSERT INTO accounts(Login, Password)
VALUES (@Login, @Password);
 
GO
