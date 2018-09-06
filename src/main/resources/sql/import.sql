insert into USERS (id, NICKNAME, POINTS) values (null, 'nickname1', 1)
insert into USERS (id, NICKNAME, POINTS) values (null, 'nickname2', 3)
insert into USERS (id, NICKNAME, POINTS) values (null, 'nickname3', 5)
insert into USERS (id, NICKNAME, POINTS) values (null, 'nickname4', 4)

insert into ROOMS (id, INFO, KEY, NAME) values (null, 'info1', 'key1', 'name1')
insert into ROOMS (id, INFO, KEY, NAME) values (null, 'info2', 'key2', 'name2')
insert into ROOMS (id, INFO, KEY, NAME) values (null, 'info3', 'key3', 'name3')
insert into ROOMS (id, INFO, KEY, NAME) values (null, 'info4', 'key4', 'name4')
insert into ROOMS (id, INFO, KEY, NAME) values (null, 'Hello there!', 'global', 'Global room')

insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 1, 1)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 2, 1)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 3, 1)

insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 1, 2)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 2, 2)

insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 1, 3)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 2, 3)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 3, 3)
insert into SIGNATURES (id, CONTENT, room_id, user_id) values (null, 'content1', 4, 3)

insert into STATISTIC (id, NAME, STRING_VALUE, INT_VALUE) values (null, 'ACTIVE_USERS', null, 0)
insert into STATISTIC (id, NAME, STRING_VALUE, INT_VALUE) values (null, 'TOTAL_USERS', null, 0)
