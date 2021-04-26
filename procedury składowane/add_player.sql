CREATE PROCEDURE add_player

@TeamID int(10) = NULL,
@Position varchar(255) = NULL,
@FirstName varchar(255) = NULL,
@Surname varchar(255) = NULL,
@MarketValue int(10) = NULL,
@Overall int(10) = NULL,
@Age int(10) = NULL

AS

DECLARE @err AS varchar(200);
IF @TeamID IS NULL OR @Position IS NULL OR @FirstName IS NULL OR 
@Surname IS NULL OR @Age IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO players(TeamID, Position, FirstName, Surname, MarketValue, Overall, Age)
VALUES (@TeamID, @Position, @FirstName, @Surname, @MarketValue, @Overall, @Age);
 
GO
