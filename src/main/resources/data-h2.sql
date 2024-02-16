insert into member(member_id, emoji, nickname) values(100, '\\u{1f601}', 'dummy');
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(100, 100, 'title1', '사랑해요', 36,36);
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(200, 100, 'title2', '사랑해요2', 36,36);
insert into post(id,
                 member_id,
                 name,
                 content,
                 latitude,
                 longitude) values(300, 100, 'title2', '사랑해요3', 36,36);
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(100,100,100,'사랑해요사랑해요');
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(200,100,100,'완전 사랑해요');
insert into post_comment(id,
                         post_id,
                         member_id,
                         comment) values(300,100,100,'완전완전 사랑해요');
insert into course(id,
                   member_id,
                   name,
                   content) values(100,100,'costtitle1', 'content1');
insert into course(id,
                   member_id,
                   name,
                   content) values(200,100,'costtitle2', 'content2');
insert into course(id,
                   member_id,
                   name,
                   content) values(300,100,'costtitle3', 'content3');

insert into course_post(id,
                        course_id,
                        post_id) values(100,100,100);
insert into course_post(id,
                        course_id,
                        post_id) values(200,100,200);
insert into course_post(id,
                        course_id,
                        post_id) values(300,100,300);