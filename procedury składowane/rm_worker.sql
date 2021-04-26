CREATE PROCEDURE rm_worker

@WorkerID int(10) = NULL,
@Login varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @WorkerID IS NULL OR @Login IS NULL OR (WorkerID FROM workers WHERE Login = @Login NOT LIKE @WorkerID)
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

DELETE FROM workers WHERE WorkerID = @WorkerID AND Login = @Login;
DELETE FROM accounts WHERE Login = @Login

GO