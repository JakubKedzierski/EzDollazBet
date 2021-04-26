CREATE PROCEDURE cancel_BookedBet

@BookedBetID int(10) = NULL,
@ClientID int(10) = NULL

AS

DECLARE @err AS varchar(200);
IF @BookedBetID IS NULL OR @ClientID IS NULL OR (ClientID FROM bookedbets WHERE BookedBetID = @BookedBetID NOT LIKE @ClientID)
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

UPDATE clients
SET Balance = Balance + (SELECT Stake FROM bookedbets WHERE BookedBetID = @BookedBetID)
WHERE ClientID = @ClientID;

DELETE FROM bookedbets WHERE BookedBetID = @BookedBetID AND ClientID = @ClientID;

GO
