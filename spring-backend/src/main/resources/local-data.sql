INSERT INTO INVITATION VALUES (1,'AAAA',5);
INSERT INTO INVITATION VALUES (2,'CCRR',2);

INSERT INTO FOOD VALUES (1,'Chicken','Grilled Chicken');
INSERT INTO FOOD VALUES (2,'Steak','Ribeye Steak');
INSERT INTO FOOD VALUES (3,'Shrimp','Grilled Shrimp');

INSERT INTO GUEST (ID,CEREMONY_ATTENDANCE,RECEPTION_ATTENDANCE,DIETARY_COMMENTS,DIETARY_CONCERNS,FIRST_NAME,ADDITIONAL_GUEST,LAST_NAME,FOOD_ID,INVITE_ID)
VALUES (1,0,1,NULL,NULL,'Hello',0,'World',NULL,1);

INSERT INTO GUEST (ID,CEREMONY_ATTENDANCE,RECEPTION_ATTENDANCE,DIETARY_COMMENTS,DIETARY_CONCERNS,FIRST_NAME,ADDITIONAL_GUEST,LAST_NAME,FOOD_ID,INVITE_ID)
VALUES (2,NULL,NULL,NULL,NULL,'World',1,'Hello',NULL,1);

INSERT INTO GUEST (ID,CEREMONY_ATTENDANCE,RECEPTION_ATTENDANCE,DIETARY_COMMENTS,DIETARY_CONCERNS,FIRST_NAME,ADDITIONAL_GUEST,LAST_NAME,FOOD_ID,INVITE_ID)
VALUES (3,NULL,NULL,NULL,NULL,'Jack',0,'Sparrow',NULL,1);

INSERT INTO GUEST (ID,CEREMONY_ATTENDANCE,RECEPTION_ATTENDANCE,DIETARY_COMMENTS,DIETARY_CONCERNS,FIRST_NAME,ADDITIONAL_GUEST,LAST_NAME,FOOD_ID,INVITE_ID)
VALUES (4,NULL,NULL,NULL,NULL,'Mickey',0,'Mouse',NULL,2);

INSERT INTO GUEST (ID,CEREMONY_ATTENDANCE,RECEPTION_ATTENDANCE,DIETARY_COMMENTS,DIETARY_CONCERNS,FIRST_NAME,ADDITIONAL_GUEST,LAST_NAME,FOOD_ID,INVITE_ID)
VALUES (5,NULL,NULL,NULL,NULL,'Minnie',0,'Mouse',NULL,2);