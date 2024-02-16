insert into member(member_id, emoji, nickname) values(1, '\\u{1f601}', 'dummy');
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(1, 1, 'title1', '사랑해요', 36,36);
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(2, 1, 'title2', '사랑해요2', 36,36);
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(3, 1, 'title2', '사랑해요3', 36,36);
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(1,1,1,'사랑해요사랑해요');
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(2,1,1,'완전 사랑해요');
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(3,1,1,'완전완전 사랑해요');
insert into course(id,
                   member_id,
                   name,
                   content) values(1,1,'costtitle1', 'content1');
insert into course(id,
                   member_id,
                   name,
                   content) values(2,1,'costtitle2', 'content2');
insert into course(id,
                   member_id,
                   name,
                   content) values(3,1,'costtitle3', 'content3');

insert into course_post(id,
                        course_id,
                        post_id) values(1,1,1);
insert into course_post(id,
                        course_id,
                        post_id) values(2,1,2);
insert into course_post(id,
                        course_id,
                        post_id) values(3,1,3);