--------------------------------------------------------
--  File created - Friday-November-17-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SUBSCRIBERS
--------------------------------------------------------

  CREATE TABLE "SCOTT"."SUBSCRIBERS" ("EMAILID" VARCHAR2(50), "BRANDNAME" VARCHAR2(20)) SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM"
REM INSERTING into SCOTT.SUBSCRIBERS
SET DEFINE OFF;
Insert into SCOTT.SUBSCRIBERS (EMAILID,BRANDNAME) values ('arijit11d@gmail.com','BYJUS');
--------------------------------------------------------
--  DDL for Index SUBSCRIBERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SUBSCRIBERS_PK" ON "SCOTT"."SUBSCRIBERS" ("EMAILID") PCTFREE 10 INITRANS 2 MAXTRANS 255  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM"
--------------------------------------------------------
--  Constraints for Table SUBSCRIBERS
--------------------------------------------------------

  ALTER TABLE "SCOTT"."SUBSCRIBERS" ADD CONSTRAINT "SUBSCRIBERS_PK" PRIMARY KEY ("EMAILID") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM"  ENABLE
  ALTER TABLE "SCOTT"."SUBSCRIBERS" MODIFY ("EMAILID" NOT NULL ENABLE)
