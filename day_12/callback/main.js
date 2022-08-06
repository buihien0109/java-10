// Cách 1 : Regular function
function sum(a, b) {
    return a + b;
}

// Cách 2 : Function expression
const sum1 = function (a, b) {
    return a + b;
}

// Cách 3 : Arrow function
const sum2 = (a, b) => {
    return a + b;
}

const sum3 = (a, b) => a + b;

console.log(sum(3, 4));
console.log(sum1(3, 4));
console.log(sum2(3, 4));
console.log(sum3(3, 4));

// Học thêm TypeScript

// Công việc 3 : Giả sử sau 3s thì thực hiện xong
function funcC() {
    setTimeout(function () {
        console.log("three");
    }, 3000)
}

// Công việc 4 : Giả sử sau 4s thì thực hiện xong
function funcD() {
    setTimeout(function () {
        console.log("four");
    }, 4000)
}

// Tổng thời gian thực hiện hết 2 cv3 và cv4 là 4s
// Thực hiện công việc
// funcD();
// funcC();

// forEach
// let numbers = [1, 2, 3, 4, 5];
// numbers.forEach((number, index) => {
//     if (index == 2) {
//         return;
//     }
//     console.log(number, index);
// })

// Mô tả công việc
// Công việc 1
const doTask1 = (name, callback) => {
    console.log("Bắt đầu công việc");
    console.log(`Thực hiện công việc : ${name}`);
    setTimeout(() => {
        callback();
    }, 3000)
}

// Công việc 2
const doTask2 = (name, callback) => {
    console.log(`Thực hiện công việc : ${name}`);
    setTimeout(() => {
        callback();
    }, 4000)
}

// Công việc 3
const doTask3 = (name, callback) => {
    console.log(`Thực hiện công việc : ${name}`);
    setTimeout(() => {
        callback();
    }, 5000)
}

// Kết thúc công việc
const finish = () => {
    console.log("Kết thúc công việc");
}

// Thực hiện cv đồng thời (nếu các cv không liên quan gì đến nhau)
// doTask1("Làm bài tập", finish);
// doTask2("Đi chơi", finish);
// doTask3("Đá bóng", finish);

// function k tên : Anonymous function
doTask1("Nhặt rau", () => {
    doTask2("Rửa rau", () => {
        doTask3("Luộc rau", finish);
    })
})