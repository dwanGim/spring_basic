-- 리그 테이블 생성
CREATE TABLE league_tbl
( 
    tno     number(10,0),
    CONSTRAINT fk_league FOREIGN KEY(tno) REFERENCES team_tbl(tno),
    name   VARCHAR2(100), 
    won    number(10,0),
    draw number(10,0) ,
    loss number(10,0),
    gf number(10,0),
    ga number(10,0),
    gd number(10,0),
    played number(10,0),
    points number(10,0),
    round VARCHAR2(100),
    qualify number(2,0) default 0
); 

----like 테이블
 CREATE sequence like_num;
 ALTER SEQUENCE like_num NOCACHE;

CREATE TABLE like_tbl (
    likeno number(10, 0),
    userid varchar2(50),
    pono number(10, 0),
    likecheck number(1, 0) default 0
);

-- likeCnt 컬럼을 post에 추가
ALTER TABLE post_tbl ADD likeCnt number(10, 0) DEFAULT 0;
SELECT * FROM post_tbl;


ALTER TABLE like_tbl ADD CONSTRAINT fk_like FOREIGN KEY(userid) REFERENCES user_tbl(userid);
ALTER TABLE like_tbl ADD CONSTRAINT fk_like_post FOREIGN KEY(pono) REFERENCES post_tbl(pono);
ALTER TABLE like_tbl ADD CONSTRAINT pk_like PRIMARY KEY(likeno);