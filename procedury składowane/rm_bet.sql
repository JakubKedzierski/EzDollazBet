CREATE PROCEDURE rm_bet

@BetID int(10) = NULL

AS

DECLARE @err AS varchar(200);
IF @BetID IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

DELETE FROM bets WHERE BetID = @BetID

GO