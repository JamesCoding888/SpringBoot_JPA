CREATE TABLE mydb.employee (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(10),
    email VARCHAR(30),
    PRIMARY KEY (id)
);

drop table mydb.employee;
drop table mydb.person;
drop table mydb.developer;
update mydb.employee set name='tim' where id=1;
insert into mydb.employee (id, name, email) values(1, 'tim', 'tim@gmail.com');
insert into mydb.employee (id, name, email) values(2, 'james', 'test@gmail.com');
insert into mydb.employee (id, name, email) values(3, 'david', 'david@gmail.com');
select * from mydb.employee;
select * from mydb.person;
select * from mydb.developer;
select * from mydb.user;
select * from mydb.id_card;
select * from mydb.person_many;
select * from mydb.person_project;
select * from mydb.project;
select * from mydb.s_project;
select * from mydb.project_embedded;
select * from mydb.T_BILLING_PERIOD;
SELECT * FROM mydb.employee_jpql_literal;
SELECT * FROM mydb.employee_native_literal;
SELECT e.name from mydb.employee_jpql_literal as e where e.name = 'ROSE';
SELECT name, salary from employee_jpql_literal;
SELECT e.id, e.name, e.salary FROM employee_native_literal e WHERE e.salary > 2000.0 ORDER BY e.salary;
SELECT e.id, e.name, e.salary FROM employee_native_literal e WHERE e.salary > 2000.0;
SELECT e.role FROM employee_native_literal e WHERE e.role = 'IT' ;
SELECT e.local_date FROM employee_native_literal e WHERE e.local_date > '2016-05-01' ;
SELECT e1.id, e1.salary FROM employee_jpql_literal e1 INNER JOIN employee_native_literal e2 ON e1.name = e2.name;
SELECT e1.id, e1.salary, e2.id, e2.salary FROM employee_jpql_literal e1 INNER JOIN employee_native_literal e2 ON e1.name = e2.name;
SELECT e1.id AS e1_id, e1.salary AS e1_salary, e2.id AS e2_id, e2.salary AS e2_salary FROM employee_jpql_literal e1 INNER JOIN employee_native_literal e2 ON e1.name = e2.name;




-- CRUDServices.queryUserInheritance_One2One(em, 3); 
    select
        programmin0_.id as id2_3_0_,
        programmin0_.first_name as first_na3_3_0_,
        programmin0_.id_card_id as id_card_6_3_0_,
        programmin0_.last_name as last_nam4_3_0_,
        programmin0_.PROG_LANG as PROG_LAN5_3_0_,
        idcard1_.id as id1_1_1_,
        idcard1_.id_number as id_numbe2_1_1_,
        idcard1_.issue_date as issue_da3_1_1_,
        idcard1_.valid as valid4_1_1_ 
    from
        user programmin0_ 
    left outer join
        id_card idcard1_ 
            on programmin0_.id_card_id=idcard1_.id 
    where
        programmin0_.id=3 
        and programmin0_.DTYPE='Programming';
        
delete from mydb.test where id=1;

-- MAXIMUM LENGTH
SELECT CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'mydb'
  AND TABLE_NAME = 'employee'
  AND COLUMN_NAME = 'name';

SHOW COLUMNS FROM employee WHERE FIELD = 'name';

SELECT CHAR_LENGTH(name) AS storage_size
FROM employee;


