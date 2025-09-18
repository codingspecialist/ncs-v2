-- 1. User (tc1, st1, admin1)
insert into teacher_tb(full_name, sign, is_verified, created_at)
values ('최주호', null, true, now());

insert into user_tb(username, email, roles, student_id, teacher_id, created_at, password)
values ('tc1', 'tc1@nate.com', 'TEACHER',null, 1, now(), '$2a$10$CmFaTHxQtJ5x9hnP8.XLouyF2Wc4HnSLA4TW6U74MdVdfNVg2B3bm');

insert into student_tb(full_name, birthday, created_at)
values ('김주희', '800825', now());

insert into user_tb(username, email, roles, student_id, teacher_id, created_at, password)
values ('st1', 'st1@nate.com', 'STUDENT',1, null, now(), '$2a$10$CmFaTHxQtJ5x9hnP8.XLouyF2Wc4HnSLA4TW6U74MdVdfNVg2B3bm');

insert into user_tb(username, email, roles, student_id, teacher_id, created_at, password)
values ('admin1', 'admin1@nate.com', 'ADMIN',null, null, now(), '$2a$10$CmFaTHxQtJ5x9hnP8.XLouyF2Wc4HnSLA4TW6U74MdVdfNVg2B3bm');

