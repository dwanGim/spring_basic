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
INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'first test title', 'first content', 'fisrt writer');
INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'second test title', 'second content', 'second writer');
INSERT INTO board_tbl (bno, title, content, writer) values (board_num.nextval, 'third test title', 'third content', 'third writer');
SELECT * FROM board_tbl;

UPDATE board_tbl SET title = '1title', content = 'content', writer = '1writer', updateDate = sysdate WHERE bno = 3;

UPDATE board_tbl SET (title, content, writer, updateDate) = (, 'wqrq', 'eqweqw', sysdate ) WHERE bno = 3;
commit;


ALTER SEQUENCE board_num NOCACHE;