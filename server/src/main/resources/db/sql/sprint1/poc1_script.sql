-- Poc 1 related scripts

-- Drop table
DROP TABLE dbo.Oasis_Poc1;

--Create Poc 1 table
CREATE TABLE dbo.Oasis_Poc1 (
	id int IDENTITY(1,1) NOT NULL,
	label_name varchar(50) NULL,
	entity_name varchar(50) NULL,
	value varchar(50) NULL,
	attribute_name varchar(50) NULL,
	saved_timestamp varchar(50) NULL,
	CONSTRAINT PK_NewTable PRIMARY KEY (id)
);

-- SQL Insert table
Select * FROM Oasis_Poc1 op;

--Delete all records
DELETE from Oasis_Poc1;

-- Inserts part of data received in getQueryAllWells()

INSERT INTO Oasis_Poc1 (label_name,entity_name,value,attribute_name,saved_timestamp) VALUES
	 ('geometryType','objectIdFieldName','spatialReference','globalIdFieldName','fields'),
	 ('OBJECTID','esriFieldTypeOID','OBJECTID','sqlTypeOther','2024-03-18 16:22:14.607'),
	 ('OGF_ID','esriFieldTypeDouble','OGF_ID','sqlTypeOther','2024-03-19 16:22:14.607'),
	 ('LICENCE_NUMBER','esriFieldTypeString','LICENCE_NUMBER','sqlTypeOther','2024-03-15 16:22:14.607'),
	 ('SPUD_DATE','esriFieldTypeDate','SPUD_DATE','sqlTypeOther','2024-03-10 16:22:14.607'),
	 ('WELL_NAME','esriFieldTypeString','WELL_NAME','sqlTypeOther','2024-03-19 16:22:14.607'),
	 ('OPERATOR','esriFieldTypeString','OPERATOR','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('WELL_TYPE','esriFieldTypeString','WELL_TYPE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('WELL_MODE','esriFieldTypeString','WELL_MODE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('SURFACE_LATITUDE_83','esriFieldTypeDouble','SURFACE_LATITUDE_83','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('SURFACE_LONGITUDE_83','esriFieldTypeDouble','SURFACE_LONGITUDE_83','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('BOTTOM_LATITUDE_83','esriFieldTypeDouble','BOTTOM_LATITUDE_83','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('BOTTOM_LONGITUDE_83','esriFieldTypeDouble','BOTTOM_LONGITUDE_83','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('LOCATION_ACCURACY','esriFieldTypeString','LOCATION_ACCURACY','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('LOCATION_SOURCE','esriFieldTypeString','LOCATION_SOURCE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('GROUND_ELEVATIO','esriFieldTypeDouble','GROUND_ELEVATIO','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('TOTAL_DEPTH','esriFieldTypeDouble','TOTAL_DEPTH','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('TRUE_VERTICAL_DEPTH','esriFieldTypeDouble','TRUE_VERTICAL_DEPTH','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('COUNTY','esriFieldTypeString','COUNTY','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('TOWNSHIP','esriFieldTypeString','TOWNSHIP','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('LOT','esriFieldTypeString','LOT','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('CONCESSIO','esriFieldTypeString','CONCESSIO','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('NORTH_SOUTH_BOUNDARY','esriFieldTypeDouble','NORTH_SOUTH_BOUNDARY','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('NORTH_SOUTH_FLG','esriFieldTypeString','NORTH_SOUTH_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('EAST_WEST_BOUNDARY','esriFieldTypeDouble','EAST_WEST_BOUNDARY','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('EAST_WEST_FLG','esriFieldTypeString','EAST_WEST_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('OFFSHORE_BLOCK','esriFieldTypeString','OFFSHORE_BLOCK','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('OFFSHORE_TRACT','esriFieldTypeString','OFFSHORE_TRACT','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('VERTICAL_FLG','esriFieldTypeString','VERTICAL_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('HORIZONTAL_FLG','esriFieldTypeString','HORIZONTAL_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('DIRECTIONAL_FLG','esriFieldTypeString','DIRECTIONAL_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('DEEPENING_FLG','esriFieldTypeString','DEEPENING_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('REENTRY_FLG','esriFieldTypeString','REENTRY_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('LATERAL_FLG','esriFieldTypeString','LATERAL_FLG','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('TOTAL_DEPTH_REACHED_DATE','esriFieldTypeDate','TOTAL_DEPTH_REACHED_DATE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('PLUGGING_END_DATE','esriFieldTypeDate','PLUGGING_END_DATE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('MAP_SYMBOL_CODE','esriFieldTypeString','MAP_SYMBOL_CODE','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('GEOMETRY_UPDATE_DATETIME','esriFieldTypeDate','GEOMETRY_UPDATE_DATETIME','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('EFFECTIVE_DATETIME','esriFieldTypeDate','EFFECTIVE_DATETIME','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('SYSTEM_DATETIME','esriFieldTypeDate','SYSTEM_DATETIME','sqlTypeOther','2024-03-21 16:22:14.607'),
	 ('SE_ANNO_CAD_DATA','esriFieldTypeBlob','SE_ANNO_CAD_DATA','sqlTypeOther','2024-03-21 16:22:14.607');

