CREATE PROCEDURE rm_player

@PlayerID int(10) = NULL,
@Surname varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @PlayerID IS NULL OR @Surname IS NULL OR (Surname FROM players WHERE PlayerID = @PlayerID NOT LIKE @Surname)
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END
 

DELETE FROM players WHERE PlayerID = @PlayerID AND Surname = @Surname;

GO