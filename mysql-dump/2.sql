insert into student (student_id, first_name, last_name)
values (1, 'Madhukar', 'Pedagani');
insert into student (student_id, first_name, last_name)
values (2, 'Suraj', 'Pedagani');
insert into student (student_id, first_name, last_name)
values (3, 'Rafal', 'Nadal');
insert into student (student_id, first_name, last_name)
values (4, 'Novak', 'Djokvic');


insert into course (course_id, course_description, course_name, instructor_name)
values (1, 'Test', 'Test', 'Test');
insert into course (course_id, course_description, course_name, instructor_name)
values (2, 'SQL', 'Test', 'Test');
insert into course (course_id, course_description, course_name, instructor_name)
values (3, 'JPA', 'Test', 'Test');
insert into course (course_id, course_description, course_name, instructor_name)
values (4, 'Spring', 'Test', 'Test');

insert into student_course (student_id, course_id)
values (1, 1);
insert into student_course (student_id, course_id)
values (1, 2);
insert into student_course (student_id, course_id)
values (1, 3);
insert into student_course (student_id, course_id)
values (1, 3);
insert into student_course (student_id, course_id)
values (2, 3);
insert into student_course (student_id, course_id)
values (2, 4);
