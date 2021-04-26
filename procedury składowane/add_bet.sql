CREATE PROCEDURE add_bet

@GameID int(10) = NULL,
@BetType int(10) = NULL,
@BettingOdd float(10) = NULL,
@Score varchar(255) = NULL,

AS

DECLARE @err AS varchar(200);
IF @GameID IS NULL OR @BetType IS NULL OR @BettingOdd IS NULL
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

INSERT INTO bets(GameID, BetType, BettingOdd, Score)
VALUES (@GameID, @BetType, @BettingOdd, @Score);
 
GO
