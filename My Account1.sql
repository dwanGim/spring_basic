CREATE SEQUENCE board_num;

CREATE TABLE board_tbl (
    bno number(10, 0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);


ALTER TABLE board_tbl ADD CONSTRAINT pk_board PRIMARY KEY(bno);

SELECT COUNT(*) FROM board_tbl;


INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'first test title', 'first content', 'fisrt writer');
INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'second test title', 'second content', 'second writer');
INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'third test title', 'third content', 'third writer');
SELECT * FROM board_tbl;

UPDATE board_tbl SET title = '1title', content = 'content', writer = '1writer', updateDate = sysdate WHERE bno = 3;

UPDATE board_tbl SET (title, content, writer, updateDate) = (, 'wqrq', 'eqweqw', sysdate ) WHERE bno = 3;
commit;

SELECT * FROM board_tbl WHERE bno = 1;

ALTER SEQUENCE board_num NOCACHE;

INSERT INTO board_tbl (bno, title, content, writer)
    (SELECT BOARD_NUM.nextval, title, content, writer from BOARD_TBL);
    



SELECT *
/*+ INDEX_DESC(board_tbl pk_board) */
FROM board_tbl ORDER BY bno DESC;


SELECT
/*+ INDEX_DESC(board_tbl pk_board) */
rownum, rowid, board_tbl.* FROM board_tbl;

SELECT
/*+ INDEX_DESC(board_tbl pk board) */
rownum rn, board_tbl.* FROM board_tbl WHERE rownum <=20;

SELECT * FROM
(SELECT /*+ INDEX_DESC(board_tbl pk_board) */
rownum rn, board_tbl.* from board_tbl WHERE rownum <=20)
WHERE rn > 10;

SELECT * FROM 
(SELECT /*+ INDEX_DESC(board_tbl pk board) */
rownum rn, board_tbl.* from board_tbl WHERE rownum <= (9 * 10))
WHERE rn > (9 - 1) * 10;



commit;


SELECT * FROM board_tbl WHERE TITLE like '%' || 'seco' || '%';


CREATE table reply_tbl (
    rno number(10, 0) ,
    bno number(10, 0) not null,
    reply varchar2(1000) not null,
    replyer varchar2(50) not null,
    replyDate date default sysdate,
    updateDate date default sysdate
    );
    
CREATE SEQUENCE reply_num;

ALTER SEQUENCE reply_num NOCACHE;

ALTER TABLE reply_tbl ADD CONSTRAINT pk_reply PRIMARY KEY(rno);

ALTER TABLE reply_tbl ADD CONSTRAINT fk_reply FOREIGN KEY (bno) REFERENCES board_tbl(bno);

SELECT * FROM reply_tbl;

commit;


CREATE TABLE tbl_test1 ( col1 varchar(50));
CREATE TABLE tbl_test2 ( col2 varchar(5));

SELECT * FROM tbl_test1;
SELECT * FROM board_tbl;
SELECT * FROM reply_tbl;

-- 댓글 개수 컬럼을 테이블에 추가했습니다.
ALTER TABLE board_tbl add(replycount number default 0);

-- 현재 엮인 댓글을 계산해서 replycount에 입력해 업데이트해주었습니다.
UPDATE board_tbl SET replycount =

    (SELECT COUNT(rno) FROM reply_tbl

    WHERE reply_tbl.bno = board_tbl.bno);
    
commit;


CREATE TABLE users (
    username varchar2(50) not null primary key,
    password varchar2(100) not null,
    enabled char(1) default '1');
    
    
CREATE TABLE authorities(
    username varchar2(50) not null,
    authority varchar2(50) not null,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username));
    
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO users (username, password) VALUES ('user00', 'pw00');
INSERT INTO users (username, password) VALUES ('member00', 'pw00');
INSERT INTO users (username, password) VALUES ('admin00', 'pw00');

INSERT INTO authorities (username, authority) VALUES ('user00', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('member00', 'ROLE_MEMBER');
INSERT INTO authorities (username, authority) VALUES ('admin00', 'ROLE_MEMBER');
INSERT INTO authorities (username, authority) VALUES ('admin00', 'ROLE_ADMIN');
commit;

SELECT * FROM users;
SELECT * FROM authorities;








