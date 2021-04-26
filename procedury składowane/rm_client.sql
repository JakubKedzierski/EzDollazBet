CREATE PROCEDURE rm_client

@ClientID int(10) = NULL,
@Login varchar(255) = NULL

AS

DECLARE @err AS varchar(200);
IF @ClientID IS NULL OR @Login IS NULL OR (ClientID FROM clients WHERE Login = @Login NOT LIKE @ClientID)
BEGIN
     SET @err = 'Invalid data!';
     RAISERROR(@err, 16,1);
     RETURN;
END

DELETE FROM clients WHERE ClientID = @ClientID AND Login = @Login;
DELETE FROM accounts WHERE Login = @Login

GO