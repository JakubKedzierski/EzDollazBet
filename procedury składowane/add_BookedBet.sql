CREATE PROCEDURE add_BookedBet

@ClientID int(10) = NULL,
@BetID int(10) = NULL,
@Stake float(10) = NULL

AS

DECLARE @err AS varchar(200);
IF @ClientID IS NULL OR @BetID IS NULL OR @Stake IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO bookedbets(ClientID, BetID, Stake)
VALUES (@ClientID, @BetID, @Stake);

UPDATE clients
SET Balance = Balance - @Stake
WHERE ClientID = @ClientID;

GO
