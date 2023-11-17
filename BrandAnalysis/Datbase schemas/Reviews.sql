--------------------------------------------------------
--  File created - Friday-November-17-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table REVIEWS
--------------------------------------------------------

  CREATE TABLE "SCOTT"."REVIEWS" ("BRANDID" NUMBER, "COMMENTS" VARCHAR2(200), "TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "RATING" NUMBER) SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM"
REM INSERTING into SCOTT.REVIEWS
SET DEFINE OFF;
Insert into SCOTT.REVIEWS (BRANDID,COMMENTS,TIME,RATING) values (5,'Good',to_timestamp('16-11-23 9:43:11.381000000 PM','DD-MM-RR fmHH12:fmMI:SSXFF AM'),5);
Insert into SCOTT.REVIEWS (BRANDID,COMMENTS,TIME,RATING) values (6,'Bad',to_timestamp('16-11-23 10:27:00.375000000 PM','DD-MM-RR fmHH12:fmMI:SSXFF AM'),1);
--------------------------------------------------------
--  Constraints for Table REVIEWS
--------------------------------------------------------

  ALTER TABLE "SCOTT"."REVIEWS" MODIFY ("RATING" NOT NULL ENABLE)
  ALTER TABLE "SCOTT"."REVIEWS" MODIFY ("COMMENTS" NOT NULL ENABLE)
  ALTER TABLE "SCOTT"."REVIEWS" MODIFY ("BRANDID" NOT NULL ENABLE)
