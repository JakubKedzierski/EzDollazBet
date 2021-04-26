CREATE PROCEDURE change_MatchResult

@GameID int(10) = NULL,
@HostGoals int(10) = NULL,
@GuestGoals int(10) = NULL,

AS

DECLARE @err AS varchar(200);
IF @GameID IS NULL OR @HostGoals IS NULL OR @GuestGoals IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

UPDATE games
SET HostGoals = @HostGoals, GuestGoals = @GuestGoals
WHERE GameID = @GameID;

GO