CREATE PROCEDURE add_game

@Host int(10) = NULL,
@Guest int(10) = NULL,
@HostGoals int(10) = NULL,
@GuestGoals int(10) = NULL,
@MatchDay DATETIME = NULL,
@MatchStatus varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @Host IS NULL OR @Guest IS NULL OR @MatchStatus IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO games(Host, Guest, HostGoals, GuestGoals, MatchDay, MatchStatus)
VALUES (@Host, @Guest, @HostGoals, @GuestGoals, @MatchDay, @MatchStatus);
 
GO
