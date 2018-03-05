var express = require('express');
var bodyParser = require('body-parser');
var mysql = require('mysql');  // 모듈을 추출합니다.
var client = mysql.createConnection({  // 데이터베이스와 연결합니다.
    user: 'afterschool',
    password: 'qwerty1234!',
    database: 'afterschool'
});
var app = express();

app.use(bodyParser.urlencoded({
    extended: true
}));

app.use(bodyParser.json());

app.listen(6060, () => {
    console.log('Example app listening on port 6060!');
});

function postResAdmin() {  // 정보등록/수정 누를 경우 : 교사 코드를 받아서 권한이 있는지 없는지 판단 후 true, false 제이슨 형태로 response
    app.post('/admin', (req, res) => {
        console.log('who get in here admin');
        var body = req.body;
        var authority;
        var authorityobj;
        console.log("body.teacher_code : " + body.teacher_code);
        if(body.teacher_code == "tc00")
            authority = '{"authority":true}';
        else
            authority = '{"authority":false}';
        authorityobj = JSON.parse(authority);
        console.log("authority : " + JSON.stringify(authorityobj));
        res.json(authorityobj);
    });
};

function postResNecessaryData() {  // 관리자 모드로 정보등록/수정 들어올 경우 & 학교 클릭할 경우 : {"necessary_data" : "school"} 을 받아서 학교 정보 모두 JSON 형태로 response, 학생 & 강좌 & 교재 & 교사 클릭 : student, class, ...
    app.post('/necessaryData', (req, res) => {
        console.log('who get in here necessaryData');
        var body = req.body;
        var sql_d = 'SELECT * FROM ' + body.necessary_data;
        console.log("body.necessary_data : " + body.necessary_data);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                console.log(result);
                res.json(result);
            }
        });
    });
};

function postResInsertSchool() {  // 학교 추가 버튼 누를 경우 : {"school_code" : "", "school_name" : "", "school_address" : "", "school_phone_number" : ""} 을 받아서 db에 insert
    app.post('/insertSchool', (req, res) => {
        console.log('who get in here insertSchool');
        var body = req.body;
        var sql_d = 'INSERT INTO school(school_code, school_name, school_address, school_phone_number) VALUES (?, ?, ?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.school_code : " + body.school_code + ", body.school_name : " + body.school_name + ", body.school_address : " + body.school_address + ", body.school_phone_number : " + body.school_phone_number);
        client.query(sql_d, [body.school_code, body.school_name, body.school_address, body.school_phone_number], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResInsertStudent() {  // 학생 추가 버튼 누를 경우 : {"student_code" : "", "student_name" : "", "school_code" : "", "school_grade" : "", "school_class" : "", "student_birth_date" : "", "student_phone_number" : "", "protector_phone_number" : "", "voucher_program_state" : ""} 을 받아서 db에 insert
    app.post('/insertStudent', (req, res) => {
        console.log('who get in here insertStudent');
        var body = req.body;
        var sql_d = 'INSERT INTO student(student_code, student_name, school_code, school_grade, school_class, student_birth_date, student_phone_number, protector_phone_number, voucher_program_state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.student_code : " + body.student_code + ", body.student_name : " + body.student_name + ", body.school_code : " + body.school_code + ", body.school_grade : " + body.school_grade + ", body.school_class : " + body.school_class + ", body.student_birth_date : " + body.student_birth_date + ", body.student_phone_number : " + body.student_phone_number + ", body.protector_phone_number : " + body.protector_phone_number + ", body.voucher_program_state : " + body.voucher_program_state);
        client.query(sql_d, [body.student_code, body.student_name, body.school_code, body.school_grade, body.school_class, body.student_birth_date, body.student_phone_number, body.protector_phone_number, body.voucher_program_state], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResInsertClass() {  // 강좌 추가 버튼 누를 경우 : {"class_code" : "", "teacher_code" : "", "class_name" : "", "school_code" : "", "class_date" : "", "class_time" : "", "textbook_code" : "", "start_date" : "", "finish_date" : "", "tuition" : ""} 을 받아서 db에 insert
    app.post('/insertClass', (req, res) => {
        console.log('who get in here insertClass');
        var body = req.body;
        var sql_d = 'INSERT INTO class(class_code, teacher_code, class_name, school_code, class_date, class_time, textbook_code, start_date, finish_date, tuition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.class_code : " + body.class_code + ", body.teacher_code : " + body.teacher_code + ", body.class_name : " + body.class_name + ", body.school_code : " + body.school_code + ", body.class_date : " + body.class_date + ", body.class_time : " + body.class_time + ", body.textbook_code : " + body.textbook_code + ", body.start_date : " + body.start_date + ", body.finish_date : " + body.finish_date + ", body.tuition : " + body.tuition);
        client.query(sql_d, [body.class_code, body.teacher_code, body.class_name, body.school_code, body.class_date, body.class_time, body.textbook_code, body.start_date, body.finish_date, body.tuition], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResInsertTextbook() {  // 교재 추가 버튼 누를 경우 : {"textbook_code" : "", "textbook_name" : "", "textbook_price" : ""} 을 받아서 db에 insert
    app.post('/insertTextbook', (req, res) => {
        console.log('who get in here insertTextbook');
        var body = req.body;
        var sql_d = 'INSERT INTO textbook(textbook_code, textbook_name, textbook_price) VALUES (?, ?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.textbook_code : " + body.textbook_code + ", body.textbook_name : " + body.textbook_name + ", body.textbook_price : " + body.textbook_price);
        client.query(sql_d, [body.textbook_code, body.textbook_name, body.textbook_price], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResInsertTeacher() {  // 강사 추가 버튼 누를 경우 : {"teacher_code" : "", "teacher_name" : "", "teacher_phone_number" : "", "teacher_email" : "", "teacher_birth_date" : "", "teacher_id" : "", "teacher_pw" : ""} 을 받아서 db에 insert
    app.post('/insertTeacher', (req, res) => {
        console.log('who get in here insertTeacher');
        var body = req.body;
        var sql_d = 'INSERT INTO teacher(teacher_code, teacher_name, teacher_phone_number, teacher_email, teacher_birth_date, teacher_id, teacher_pw) VALUES (?, ?, ?, ?, ?, ?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.teacher_code : " + body.teacher_code + ", body.teacher_name : " + body.teacher_name + ", body.teacher_phone_number : " + body.teacher_phone_number + ", body.teacher_email : " + body.teacher_email + ", body.teacher_birth_date : " + body.teacher_birth_date + ", body.teacher_id : " + body.teacher_id + ", body.teacher_pw : " + body.teacher_pw);
        client.query(sql_d, [body.teacher_code, body.teacher_name, body.teacher_phone_number, body.teacher_email, body.teacher_birth_date, body.teacher_id, body.teacher_pw], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResInsertEnroll() {  // 수강 추가 : {"class_code" : "", "student_code" : ""} 을 받아서 db에 insert
    app.post('/insertEnroll', (req, res) => {
        console.log('who get in here insertEnroll');
        var body = req.body;
        var sql_d = 'INSERT INTO enroll(class_code, student_code) VALUES (?, ?)';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body : " + JSON.stringify(body));
        console.log("body.class_code : " + body.class_code + ", body.student_code : " + body.student_code);
        client.query(sql_d, [body.class_code, body.student_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateSchool() {  // 학교 정보 수정 : {"school_code" : "", "school_name" : "", "school_address" : "", "school_phone_number" : ""} 를 받아서 db에 update
    app.post('/updateSchool', (req, res) => {
        console.log('who get in here updateSchool');
        var body = req.body;
        var sql_d = 'UPDATE school SET school_name=?, school_address=?, school_phone_number=? WHERE school_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.school_code : " + body.school_code + ", body.school_name : " + body.school_name + ", body.school_address : " + body.school_address + ", body.school_phone_number : " + body.school_phone_number);
        client.query(sql_d, [body.school_name, body.school_address, body.school_phone_number, body.school_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateStudent() {  // 학생 정보 수정 : {"student_code" : "", "student_name" : "", "school_code" : "", "school_grade" : "", "school_class" : "", "student_birth_date" : "", "student_phone_number" : "", "protector_phone_number" : "", "voucher_program_state" : ""} 을 받아서 db에 update
    app.post('/updateStudent', (req, res) => {
        console.log('who get in here updateStudent');
        var body = req.body;
        var sql_d = 'UPDATE student SET student_name=?, school_code=?, school_grade=?, school_class=?, student_birth_date=?, student_phone_number=?, protector_phone_number=?, voucher_program_state=? WHERE student_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.student_code : " + body.student_code + ", body.student_name : " + body.student_name + ", body.school_code : " + body.school_code + ", body.school_grade : " + body.school_grade + ", body.school_class : " + body.school_class + ", body.student_birth_date : " + body.student_birth_date + ", body.student_phone_number : " + body.student_phone_number + ", body.protector_phone_number : " + body.protector_phone_number + ", body.voucher_program_state : " + body.voucher_program_state);
        client.query(sql_d, [body.student_name, body.school_code, body.school_grade, body.school_class, body.student_birth_date, body.student_phone_number, body.protector_phone_number, body.voucher_program_state, body.student_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateClass() {  // 강좌 정보 수정 : {"class_code" : "", "teacher_code" : "", "class_name" : "", "school_code" : "", "class_date" : "", "class_time" : "", "textbook_code" : "", "start_date" : "", "finish_date" : "", "tuition" : ""} 을 받아서 db에 update
    app.post('/updateClass', (req, res) => {
        console.log('who get in here updateClass');
        var body = req.body;
        var sql_d = 'UPDATE class SET teacher_code=?, class_name=?, school_code=?, class_date=?, class_time=?, textbook_code=?, start_date=?, finish_date=?, tuition=? WHERE class_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.class_code : " + body.class_code + ", body.teacher_code : " + body.teacher_code + ", body.class_name : " + body.class_name + ", body.school_code : " + body.school_code + ", body.class_date : " + body.class_date + ", body.class_time : " + body.class_time + ", body.textbook_code : " + body.textbook_code + ", body.start_date : " + body.start_date + ", body.finish_date : " + body.finish_date + ", body.tuition : " + body.tuition);
        client.query(sql_d, [body.teacher_code, body.class_name, body.school_code, body.class_date, body.class_time, body.textbook_code, body.start_date, body.finish_date, body.tuition, body.class_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateTextbook() {  // 교재 정보 수정 : {"textbook_code" : "", "textbook_name" : "", "textbook_price" : ""} 을 받아서 db에 update
    app.post('/updateTextbook', (req, res) => {
        console.log('who get in here updateTextbook');
        var body = req.body;
        var sql_d = 'UPDATE textbook SET textbook_name=?, textbook_price=? WHERE textbook_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.textbook_code : " + body.textbook_code + ", body.textbook_name : " + body.textbook_name + ", body.textbook_price : " + body.textbook_price);
        client.query(sql_d, [body.textbook_name, body.textbook_price, body.textbook_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateTeacher() {  // 강사 정보 수정 : {"teacher_code" : "", "teacher_name" : "", "teacher_phone_number" : "", "teacher_email" : "", "teacher_birth_date" : "", "teacher_id" : "", "teacher_pw" : ""} 을 받아서 db에 update
    app.post('/updateTeacher', (req, res) => {
        console.log('who get in here updateTeacher');
        var body = req.body;
        var sql_d = 'UPDATE teacher SET teacher_name=?, teacher_phone_number=?, teacher_email=?, teacher_birth_date=?, teacher_id=?, teacher_pw=? WHERE teacher_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.teacher_code : " + body.teacher_code + ", body.teacher_name : " + body.teacher_name + ", body.teacher_phone_number : " + body.teacher_phone_number + ", body.teacher_email : " + body.teacher_email + ", body.teacher_birth_date : " + body.teacher_birth_date + ", body.teacher_id : " + body.teacher_id + ", body.teacher_pw : " + body.teacher_pw);
        client.query(sql_d, [body.teacher_name, body.teacher_phone_number, body.teacher_email, body.teacher_birth_date, body.teacher_id, body.teacher_pw, body.teacher_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResUpdateEnroll() {  // 납부 일자 수정 : {"class_code" : "", "student_code" : "", "tuition_date" : "", "textbook_date" : ""} 을 받아서 db에 수강료, 교재비 납부일자 update
    app.post('/updateEnroll', (req, res) => {
        console.log('who get in here updateEnroll');
        var body = req.body;
        var sql_d = 'UPDATE enroll SET tuition_date=?, textbook_date=? WHERE class_code=? AND teacher_code=?';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.class_code : " + body.class_code + ", body.teacher_code : " + body.teacher_code + ", body.tuition_date : " + body.tuition_date + ", body.textbook_date : " + body.textbook_date);
        client.query(sql_d, [body.tuition_date, body.textbook_date, body.class_code, body.teacher_code], function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResDeleteData() {  // 삭제 버튼 누를 경우 : {"delete_data" : "school", "data_code" : "sc01"} 이런식으로 받아서 db에 delete
    app.post('/deleteData', (req, res) => {
        console.log('who get in here deleteData');
        var body = req.body;
        var sql_d = 'DELETE FROM ' + body.delete_data + ' WHERE ' + body.delete_data + '_code="' + body.data_code + '"';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.delete_data : " + body.delete_data + ", body.data_code : " + body.data_code);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResDeleteEnroll() {  // 수강 삭제 : {"class_code" : "c20171101", "student_code" : "st2017"} 이런식으로 받아서 db에 delete
    app.post('/deleteEnroll', (req, res) => {
        console.log('who get in here deleteEnroll');
        var body = req.body;
        var sql_d = 'DELETE FROM enroll WHERE class_code="' + body.class_code + '" AND student_code="' + body.student_code + '"';
        var queryobj = JSON.parse('{"query":true}');
        console.log("body.class_code : " + body.class_code + ", body.student_code : " + body.student_code);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                res.json(queryobj);
                console.log(result);
            }
        });
    });
};

function postResClass() {  // 강좌 클릭했을 경우 : {"class_code" : "c20171101"} 이런식으로 받아서 강좌를 듣고 있는 모든 학생 정보 JSON 형태로 response
    app.post('/class', (req, res) => {
        console.log('who get in here class');
        var body = req.body;
        var sql_d = 'SELECT student.student_code, student.student_name, student.school_code, student.school_grade, student.school_class, student.student_birth_date, student.student_phone_number, student.protector_phone_number, student.voucher_program_state FROM student NATURAL JOIN enroll WHERE enroll.class_code = "' + body.class_code + '" AND enroll.student_code = student.student_code';
        console.log("body : " + JSON.stringify(body));
        console.log("body.class_code : " + body.class_code);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                console.log(result);
                res.json(result);
            }
        });
    });
};

function postResPayment() {  // 납부확인 클릭했을 경우 : {"teacher_code" : "tc01"} 이런식으로 받아서 납부 정보 JSON 형태로 response
    app.post('/payment', (req, res) => {
        console.log('who get in here payment');
        var body = req.body;
        var sql_d = 'SELECT enroll.class_code, class.class_name, enroll.student_code, student.protector_phone_number, student.student_name, enroll.tuition_date FROM enroll, student, class WHERE enroll.class_code = class.class_code and student.student_code = enroll.student_code and class.teacher_code = "' + body.teacher_code + '"';    
        
        console.log("body.teacher_code : " + body.teacher_code);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                console.log(result);
                res.json(result);
            }
        });
    });
};

function postResSettlement() {  // 결산관리 클릭했을 경우 : {"teacher_code" : "tc01"} 이런식으로 받아서 결산 정보 JSON 형태로 response
    app.post('/settlement', (req, res) => {
        console.log('who get in here settlement');
        var body = req.body;
        var sql_d = 'SELECT resultTable.class_code, resultTable.class_name, resultTable.tuition, textbook.textbook_price, resultTable.student_cnt FROM (SELECT class.class_code AS class_code, class.class_name AS class_name, class.tuition AS tuition, class.teacher_code AS tc, class.textbook_code AS ttc, countTable.student_cnt FROM class LEFT OUTER JOIN (SELECT COUNT(*) AS student_cnt, enroll.class_code AS class_code FROM enroll, (SELECT class.class_code AS c_code FROM class WHERE class.teacher_code = "' + body.teacher_code + '")temp WHERE enroll.class_code = temp.c_code GROUP BY enroll.class_code)countTable ON class.class_code = countTable.class_code)resultTable NATURAL JOIN textbook WHERE (resultTable.ttc = textbook.textbook_code) AND (resultTable.tc = "' + body.teacher_code + '")';
        console.log("body.teacher_code : " + body.teacher_code);
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                console.log(result);
                res.json(result);
            }
        });
    });
};

function postResUpdatePayment() {  // 납부확인 체크 클릭했을 경우 : {"student_code" : "st00000"},{"class_code" : "c20171101"},{"tuition_date","1234-12-12"} 이런식으로 받아서 결산 정보 JSON 형태로 response
    app.post('/updatepayment', (req, res) => {
        console.log('who get in here settlement');
        var body = req.body;
        var sql_d = 'UPDATE enroll set tuition_date = "'+ body.tuition_date +'" where student_code = "'+ body.student_code + '" and class_code= "'+ body.class_code+'"';
        console.log("body.student_code : " + body.student_code + "body.class_code : " + body.class_code );
        client.query(sql_d, function (error, result, fields) {
            if (error) {
                console.log('query error');
            }
            else {
                console.log(result);
                res.json(result);
            }
        });
    });
};

postResAdmin();  // 정보등록/수정 누를 경우 : 교사 코드를 받아서 권한이 있는지 없는지 판단 후 true, false 제이슨 형태로 response

postResNecessaryData();  // 관리자 모드로 정보등록/수정 들어올 경우 & 학교 클릭할 경우 : {"necessary_data" : "school"} 을 받아서 학교 정보 모두 JSON 형태로 response, 학생 & 강좌 & 교재 & 교사 클릭 : student, class, textbook, teacher

postResInsertSchool();  // 학교 추가 버튼 누를 경우 : {"school_code" : "", "school_name" : "", "school_address" : "", "school_phone_number" : ""} 을 받아서 db에 insert
postResInsertStudent();    // 학생 추가 버튼 누를 경우 : {"student_code" : "", "student_name" : "", "school_code" : "", "school_grade" : "", "school_class" : "", "student_birth_date" : "", "student_phone_number" : "", "protector_phone_number" : "", "voucher_program_state" : ""} 을 받아서 db에 insert
postResInsertClass();  // 강좌 추가 버튼 누를 경우 : {"class_code" : "", "teacher_code" : "", "class_name" : "", "school_code" : "", "class_date" : "", "class_time" : "", "textbook_code" : "", "start_date" : "", "finish_date" : "", "tuition" : ""} 을 받아서 db에 insert
postResInsertTextbook();  // 교재 추가 버튼 누를 경우 : {"textbook_code" : "", "textbook_name" : "", "textbook_price" : ""} 을 받아서 db에 insert
postResInsertTeacher();  // 강사 추가 버튼 누를 경우 : {"teacher_code" : "", "teacher_name" : "", "teacher_phone_number" : "", "teacher_email" : "", "teacher_birth_date" : "", "teacher_id" : "", "teacher_pw" : ""} 을 받아서 db에 insert
postResInsertEnroll();  // 수강 추가 : {"class_code" : "", "student_code" : ""} 을 받아서 db에 insert

postResUpdateSchool();  // 학교 정보 수정 : {"school_code" : "", "school_name" : "", "school_address" : "", "school_phone_number" : ""} 를 받아서 db에 update
postResUpdateStudent();  // 학생 정보 수정 : {"student_code" : "", "student_name" : "", "school_code" : "", "school_grade" : "", "school_class" : "", "student_birth_date" : "", "student_phone_number" : "", "protector_phone_number" : "", "voucher_program_state" : ""} 을 받아서 db에 update
postResUpdateClass();  // 강좌 정보 수정 : {"class_code" : "", "teacher_code" : "", "class_name" : "", "school_code" : "", "class_date" : "", "class_time" : "", "textbook_code" : "", "start_date" : "", "finish_date" : "", "tuition" : ""} 을 받아서 db에 update
postResUpdateTextbook();  // 교재 정보 수정 : {"textbook_code" : "", "textbook_name" : "", "textbook_price" : ""} 을 받아서 db에 update
postResUpdateTeacher();  // 강사 정보 수정 : {"teacher_code" : "", "teacher_name" : "", "teacher_phone_number" : "", "teacher_email" : "", "teacher_birth_date" : "", "teacher_id" : "", "teacher_pw" : ""} 을 받아서 db에 update
postResUpdateEnroll();  // 납부 일자 수정 : {"class_code" : "", "student_code" : "", "tuition_date" : "", "textbook_date" : ""} 을 받아서 db에 수강료, 교재비 납부일자 update

postResDeleteData();  // 삭제 버튼 누를 경우 : {"delete_data" : "school", "data_code" : "sc01"} 이런식으로 받아서 db에 delete
postResDeleteEnroll();  // 수강 삭제 : {"class_code" : "c20171101", "student_code" : "st2017"} 이런식으로 받아서 db에 delete

postResClass();  // 강좌 클릭했을 경우 : {"class_code" : "c20171101"} 이런식으로 받아서 강좌를 듣고 있는 모든 학생 정보 JSON 형태로 response

postResPayment();  // 납부확인 클릭했을 경우 : {"teacher_code" : "tc01"} 이런식으로 받아서 납부 정보 JSON 형태로 response

postResSettlement();  // 결산관리 클릭했을 경우 : {"teacher_code" : "tc01"} 이런식으로 받아서 결산 정보 JSON 형태로 response
postResUpdatePayment(); // 납부확인 체크 클릭했을 경우 : {"student_code" : "st00000"},{"class_code" : "c20171101"},{"tuition_date","1234-12-12"} 이런식으로 받아서 결산 정보 JSON 형태로 response