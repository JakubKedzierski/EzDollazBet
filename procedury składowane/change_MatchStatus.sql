CREATE PROCEDURE change_MatchStatus

@GameID int(10) = NULL,
@MatchStatus varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @GameID IS NULL OR @MatchStatus IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

UPDATE games
SET MatchStatus = @MatchStatus
WHERE GameID = @GameID;
 
GO