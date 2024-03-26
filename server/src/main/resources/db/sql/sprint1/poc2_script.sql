-- Poc 2 related scripts

-- Drop table 
drop table dbo.Oasis_Poc2;

-- Create table 
CREATE TABLE dbo.Oasis_Poc2 (
	id int IDENTITY(1,1) NOT NULL,
	label_name varchar(50) NULL,
	entity_name varchar(50) NULL,
	value varchar(50) NULL,
	control_type varchar(50) NULL,
	attribute_name varchar(50) NULL,
	tooltip varchar(50) NULL,
	saved_timestamp datetime NULL,
	CONSTRAINT PK_Oasis_Poc2 PRIMARY KEY (id)
);

-- Delete all records
Delete from Oasis_Poc2 ;

-- Insert dummy data
INSERT INTO Oasis_Poc2 (label_name,entity_name,value,control_type, attribute_name, tooltip, saved_timestamp) VALUES
	 ('First Name','site','John','varchar', 'fname','Dynamic Form First Name',GETDATE()),
	 ('Last Name','site','Doss','varchar','lname','Dynamic Form Last Name',GETDATE()),
	 ('Email','site','John.Doss@abc.com','varchar','email','Dynamic Form Email',GETDATE()),
	 ('Weight','site','35','double','weight','Dynamic Form Weight',GETDATE()),
	 ('Height','site','120','double','height','Dynamic Form Height',GETDATE()),
	 ('Gender','site','M','varchar','gender','Dynamic Form Gender',GETDATE()),
	 ('Date','site','2024-03-25','date','date','Dynamic Form Date',GETDATE());

