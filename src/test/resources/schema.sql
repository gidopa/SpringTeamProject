drop table if exists item CASCADE;
create TABLE Customer(
    customer_id varchar(100) primary key,
    password varchar(30),
    name varchar(100),
    email varchar(255) UNIQUE ,
    phone_number varchar(50) UNIQUE ,
    address varchar(255)
    );

-- 해당 경로에 해당 파일 이름으로 만들면 메모리가 뜨면서 DB를 초기화 해준다 (Embeded ? ) 테스트할때 이용하자