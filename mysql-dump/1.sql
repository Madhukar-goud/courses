-- auto-generated definition
create table course
(
    course_id          bigint auto_increment
        primary key,
    course_description varchar(255) null,
    course_name        varchar(255) null,
    instructor_name    varchar(255) null
);

-- auto-generated definition
create table student
(
    student_id bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

-- auto-generated definition
create table student_course
(
    student_id bigint not null,
    course_id  bigint not null,
    constraint FKejrkh4gv8iqgmspsanaji90ws
        foreign key (course_id) references course (course_id),
    constraint FKq7yw2wg9wlt2cnj480hcdn6dq
        foreign key (student_id) references student (student_id)
);

