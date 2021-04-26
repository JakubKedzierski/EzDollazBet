CREATE PROCEDURE add_worker

@Login varchar(255) = NULL,
@Password varchar(255) = NULL,
@FirstName varchar(255) = NULL,
@Surname varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @Login IS NULL OR @Password IS NULL OR @FirstName IS NULL OR
@Surname IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO workers(Login, FirstName, Surname)
VALUES (@Login, @FirstName, @Surname);

INSERT INTO accounts(Login, Password)
VALUES (@Login, @Password);
 
GO
